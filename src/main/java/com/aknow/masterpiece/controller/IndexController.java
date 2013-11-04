package com.aknow.masterpiece.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.service.IndexService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

public class IndexController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		IndexService service = new IndexService();
		Integer unread = new Integer(0);

		HttpSession session = this.request.getSession();

		if(session.getAttribute("logon") == null){
			session.setAttribute("logon", Boolean.FALSE);
		}else if(((Boolean)session.getAttribute("logon")).booleanValue()){
			String loginIdInSession = (String) session.getAttribute("loginID");
			Map<String, Object> user = UtilityMethods.getUser(loginIdInSession);
			requestScope("user", user);

			//未読メッセージの件数を取得
			unread = Memcache.get(Consts.UnreadMessageNumber_KEY + loginIdInSession);
			if(unread == null){
				unread = UtilityMethods.getUnreadMessageNumber(loginIdInSession);
				Memcache.put(Consts.UnreadMessageNumber_KEY + loginIdInSession, unread);
			}
		}
		requestScope("unread", unread);

		//スライドショーの情報取得
		List<Map<String, Object>> slideList = Memcache.get(Consts.SlideDataList_KEY);
		if(slideList == null){
			slideList = service.getSlideList();
			Memcache.put(Consts.SlideDataList_KEY, slideList);
		}
		requestScope("slideList", slideList);

		//新着６アイテムの情報取得
		List<Map<String, Object>> latestList = Memcache.get(Consts.LatestDataList_KEY);
		if(latestList == null){
			latestList = service.getLatestList();
			Memcache.put(Consts.LatestDataList_KEY, latestList);
		}
		requestScope("latestList", latestList);


		//アクティビティ情報（全体）の取得
		IndexService indexService = new IndexService();
		List<Activity> activityList = Memcache.get(Consts.AllActivityData_KEY);
		List<Map<String, Object>> activityUserList = null;
		if(activityList == null){
			activityList = indexService.getActivityList();
			Memcache.put(Consts.AllActivityData_KEY, activityList);
		}
		activityUserList = indexService.getActivityUserList(activityList);
		requestScope("activityList", activityList);
		requestScope("activityUserList", activityUserList);


		//カテゴリごとのアイテム数の取得
		Map<String, Integer> countByCategoryMap = Memcache.get(Consts.CountByCategoryMap_KEY);
		if(countByCategoryMap == null){
			countByCategoryMap = UtilityMethods.getCountByCategory();
			Memcache.put(Consts.CountByCategoryMap_KEY, countByCategoryMap);
		}
		requestScope("countByCategoryMap", countByCategoryMap);


		return forward("index.jsp");
	}
}