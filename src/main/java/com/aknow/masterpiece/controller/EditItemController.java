package com.aknow.masterpiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;
import com.aknow.masterpiece.service.EditItemService;
import com.aknow.masterpiece.service.UserTopService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;
import com.google.appengine.api.datastore.KeyFactory;

public class EditItemController extends Controller {

    @Override
    public Navigation run() throws Exception {

        try{
            this.request.setCharacterEncoding("UTF-8");

            String category = this.request.getParameter("editItemcategory");
            byte[] outTestStrBtye = Base64.decodeBase64(this.request.getParameter("editItemNameHidden").getBytes());
            String name = new String(outTestStrBtye, "utf-8");
            outTestStrBtye = Base64.decodeBase64(this.request.getParameter("editItemCommentHidden").getBytes());
            String comment = new String(outTestStrBtye, "utf-8");
            String url = this.request.getParameter("editItemUrl");
            String key = this.request.getParameter("editTargetItemKey");

            HttpSession session = this.request.getSession();
            String loginIdInSession = (String) session.getAttribute("loginID");//ログイン中のID
            String editTargetLoginID = this.request.getParameter("editTargetLoginID");
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
            User user = topService.getUser(editTargetLoginID);

            Map<String, Object> userMap = Memcache.get(Consts.UserMapInfoForUserPage_KEY + user.getLoginID());
            if(userMap == null){
                userMap = topService.getUser(user);
                Memcache.put(Consts.UserMapInfoForUserPage_KEY + user.getLoginID(), userMap);
            }
            requestScope("user", userMap);

            List<Object> activityDatasByUser = Memcache.get(Consts.ActivityDatasByUser_KEY + user.getLoginID());
            if(activityDatasByUser == null){
                List<Activity> activityList = topService.getActivityList(user.getLoginID());
                List<Map<String, Object>> activityUserList = topService.getActivityUserList(activityList, user);
                activityDatasByUser = new ArrayList<Object>();
                activityDatasByUser.add(activityList);
                activityDatasByUser.add(activityUserList);
                Memcache.put(Consts.ActivityDatasByUser_KEY + user.getLoginID(), activityDatasByUser);
            }
            requestScope("activityList", activityDatasByUser.get(0));
            requestScope("activityUserList", activityDatasByUser.get(1));

            //投稿済みアイテムの編集
            EditItemService service = new EditItemService();

            Item item = service.editItem(loginIdInSession, editTargetLoginID, category, name, comment, url, key);
            if(item != null){
                requestScope("loginID_this_page", editTargetLoginID);
                requestScope("itemName", name);
                requestScope("categoryName", UtilityMethods.getCategoryName(category));
                requestScope("itemKey", KeyFactory.keyToString(item.getKey()));
                return forward("/edit_success.jsp");
            }

            return forward("/edit_fail.jsp");//TODO もうちょい親切に？
        }catch(Exception e){
            throw UtilityMethods.sendErrorMail(this.getClass().getName(), e);
        }
    }
}
