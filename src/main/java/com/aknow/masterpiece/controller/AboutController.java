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

public class AboutController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		HttpSession session = this.request.getSession();
		String requestLoginId = this.request.getParameter("loginID");
		String loginIdInSession = (String) session.getAttribute("loginID");
		session.setAttribute("loginError", "0");

		requestScope("loginID_this_page", requestLoginId);
		requestScope("loginIdInSession", loginIdInSession);

		Integer unread = new Integer(0);
		if(session.getAttribute("logon") == null){
			session.setAttribute("logon", Boolean.FALSE);
		}else if(((Boolean)session.getAttribute("logon")).booleanValue()){
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

		return forward("/about.jsp");

	}
}
