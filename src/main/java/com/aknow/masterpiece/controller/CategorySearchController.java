package com.aknow.masterpiece.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.service.CategorySearchService;
import com.aknow.masterpiece.service.IndexService;
import com.aknow.masterpiece.service.UserTopService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

public class CategorySearchController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		HttpSession session = this.request.getSession();
		String requestLoginId = asString("loginID");
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

		//カテゴリ検索実施・その結果をセット
		String categoryCode = asString("categoryCode");
		String pageNo = asString("pageNo");
		CategorySearchService service = new CategorySearchService();
		UserTopService userService = new UserTopService();

		List<Item> itemList = service.getItemList(categoryCode, Integer.parseInt(pageNo));
		List<String> itemKeyList = userService.getItemKeyList(itemList);

		int itemCount = service.getItemCount(categoryCode);

		int pageCount = 0;
		if((itemCount % 30) == 0){
			pageCount = (itemCount / 30);
		}else{
			pageCount = (itemCount / 30) + 1;
		}
		requestScope("categoryName", UtilityMethods.getCategoryName(categoryCode));
		requestScope("categoryCode", categoryCode);
		requestScope("itemList_list", itemList);
		requestScope("itemKeyList", itemKeyList);
		requestScope("itemCount", Integer.valueOf(itemCount));
		requestScope("itemCount_top", Integer.valueOf(itemCount - (Integer.parseInt(pageNo) - 1) * 30));
		requestScope("pageCount", Integer.valueOf(pageCount));
		requestScope("currentPage", Integer.decode(pageNo));

		return forward("/categorySearchResult.jsp");

	}
}
