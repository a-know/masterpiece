package com.aknow.masterpiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;
import com.aknow.masterpiece.service.PostItemService;
import com.aknow.masterpiece.service.UserTopService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;

public class PostItemController extends BaseController {

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();


	@Override
	public Navigation runImpl() throws Exception {

		this.request.setCharacterEncoding("UTF-8");
		
		@SuppressWarnings("unchecked")
		Map<String, String[]> paramMap = this.request.getParameterMap();

		@SuppressWarnings("deprecation")
		Map<String, BlobKey> blobs = this.blobstoreService.getUploadedBlobs(this.request);
		String category = paramMap.get("postItemcategory")[0];
		byte[] outTestStrBtye = Base64.decodeBase64(paramMap.get("postItemNameHidden")[0].getBytes());
		String name = new String(outTestStrBtye, "utf-8");
		outTestStrBtye = Base64.decodeBase64(paramMap.get("postItemCommentHidden")[0].getBytes());
		String comment = new String(outTestStrBtye, "utf-8");
		String url = paramMap.get("postItemUrl")[0];
		String isTweet = "off";
		if (paramMap.get("postWithTwitter") != null){
			isTweet = paramMap.get("postWithTwitter")[0];
		}
		String tweetContent = "";
		if("on".equals(isTweet)){
			outTestStrBtye = Base64.decodeBase64(paramMap.get("tweetContentHidden")[0].getBytes());
			tweetContent = new String(outTestStrBtye, "utf-8");
		}

		HttpSession session = this.request.getSession();
		String loginIdInSession = (String) session.getAttribute("loginID");//ログイン中のID
		String postTargetLoginID = paramMap.get("postTargetLoginID")[0];
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

		//サイドの情報取得
		UserTopService topService = new UserTopService();

		User user = topService.getUser(postTargetLoginID);
		Map<String, Object> userMap = topService.getUser(user);
		requestScope("user", userMap);

		//get activity info this user
		List<Object> activityDatasByUser = Memcache.get(Consts.ActivityDatasByUser_KEY + postTargetLoginID);
		if(activityDatasByUser == null){
			List<Activity> activityList = topService.getActivityList(postTargetLoginID);
			List<Map<String, Object>> activityUserList = topService.getActivityUserList(activityList, user);
			activityDatasByUser = new ArrayList<Object>();
			activityDatasByUser.add(activityList);
			activityDatasByUser.add(activityUserList);
			Memcache.put(Consts.ActivityDatasByUser_KEY + postTargetLoginID, activityDatasByUser);
		}
		requestScope("activityList", activityDatasByUser.get(0));
		requestScope("activityUserList", activityDatasByUser.get(1));


		PostItemService service = new PostItemService();

		Item item = service.postItem(loginIdInSession, postTargetLoginID, blobs, category, name, comment, url, isTweet, tweetContent);
		if(item != null){
			requestScope("loginID_this_page", postTargetLoginID);
			requestScope("itemName", name);
			requestScope("categoryName", UtilityMethods.getCategoryName(category));
			requestScope("itemKey", KeyFactory.keyToString(item.getKey()));

			//delete memcache
			Memcache.delete(Consts.CountByCategoryMap_KEY);
			Memcache.delete(Consts.SlideDataList_KEY);
			Memcache.delete(Consts.LatestDataList_KEY);
			Memcache.delete(Consts.MontageImageList_KEY + user.getLoginID());
			Memcache.delete(Consts.ItemListForUserTop_KEY + user.getLoginID());

			return forward("/post_success.jsp");
		}

		return forward("/post_fail.jsp");//TODO もうちょい親切に？
	}
}