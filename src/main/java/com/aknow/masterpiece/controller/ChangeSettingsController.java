package com.aknow.masterpiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.User;
import com.aknow.masterpiece.service.ChangeSettingsService;
import com.aknow.masterpiece.service.UserTopService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;

public class ChangeSettingsController extends BaseController {

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	@SuppressWarnings({ "deprecation" })
	@Override
	public Navigation runImpl() throws Exception {

		requestScope("settingsChangeSuccess", "");

		Map<String, BlobKey> blobs = null;
		if("exists".equals(asString("iconFileExists"))){
			blobs = this.blobstoreService.getUploadedBlobs(this.request);
		}

		String url = asString("inputUrl");
		byte[] outTestStrBtye = Base64.decodeBase64(asString("inputIntroductionHidden").getBytes());
		String introduction = new String(outTestStrBtye, "utf-8");

		HttpSession session = this.request.getSession();
		String loginIdInSession = (String) session.getAttribute("loginID");//ログイン中のID
		String targetLoginID = asString("settingTargetLoginID");
		session.setAttribute("loginError", "0");

		Integer unread = new Integer(0);
		if(loginIdInSession != null){
			//未読メッセージの件数を取得
			unread = Memcache.get(Consts.UnreadMessageNumber_KEY + loginIdInSession);
			if(unread == null){
				unread = UtilityMethods.getUnreadMessageNumber(loginIdInSession);
				Memcache.put(Consts.UnreadMessageNumber_KEY + loginIdInSession, unread);
			}
		}
		requestScope("unread", unread);

		//カテゴリごとのアイテム数の取得
		Map<String, Integer> countByCategoryMap = Memcache.get(Consts.CountByCategoryMap_KEY);
		if(countByCategoryMap == null){
			countByCategoryMap = UtilityMethods.getCountByCategory();
			Memcache.put(Consts.CountByCategoryMap_KEY, countByCategoryMap);
		}
		requestScope("countByCategoryMap", countByCategoryMap);

		ChangeSettingsService service = new ChangeSettingsService();
		User user = service.changeSettings(loginIdInSession, targetLoginID, url, introduction, blobs);

		//get activity info this user
		UserTopService topService = new UserTopService();
		List<Object> activityDatasByUser = Memcache.get(Consts.ActivityDatasByUser_KEY + targetLoginID);
		if(activityDatasByUser == null){
			List<Activity> activityList = topService.getActivityList(targetLoginID);
			List<Map<String, Object>> activityUserList = topService.getActivityUserList(activityList, user);
			activityDatasByUser = new ArrayList<Object>();
			activityDatasByUser.add(activityList);
			activityDatasByUser.add(activityUserList);
			Memcache.put(Consts.ActivityDatasByUser_KEY + targetLoginID, activityDatasByUser);
		}
		requestScope("activityList", activityDatasByUser.get(0));
		requestScope("activityUserList", activityDatasByUser.get(1));

		if(user == null){
			return forward("/settings_fail.jsp");//TODO もうちょい親切に？
		}

		if(user.getIconFileBlobKey() == null){
			user.setImageUrl("/images/default_icon.png");
		}else{
			String userImageUrl = Memcache.get(Consts.UserImageUrl_KEY + targetLoginID);
			if(userImageUrl == null){
				ImagesService imagesService = ImagesServiceFactory.getImagesService();
				userImageUrl = imagesService.getServingUrl(user.getIconFileBlobKey(), 48, false);
				Memcache.put(Consts.UserImageUrl_KEY + targetLoginID, userImageUrl);
			}
			user.setImageUrl(userImageUrl);
		}

		//setting change done. memcache delete
		Memcache.delete(Consts.UserMapInfoForUserPage_KEY + user.getLoginID());

		Map<String, Object> userMap = Memcache.get(Consts.UserMapInfoForUserPage_KEY + user.getLoginID());
		if(userMap == null){
			userMap = topService.getUser(user);
			Memcache.put(Consts.UserMapInfoForUserPage_KEY + user.getLoginID(), userMap);
		}
		requestScope("user", userMap);
		requestScope("settingsChangeSuccess", "success");
		requestScope("loginID_this_page", targetLoginID);
		requestScope("loginIdInSession", loginIdInSession);

		return forward("/settings.jsp");
	}
}
