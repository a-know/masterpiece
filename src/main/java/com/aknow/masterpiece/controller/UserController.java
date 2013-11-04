package com.aknow.masterpiece.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;
import com.aknow.masterpiece.service.UserTopService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

public class UserController extends Controller {

    @SuppressWarnings({ "boxing", "unchecked" })
    @Override
    public Navigation run() throws Exception {


        try{
            HttpSession session = this.request.getSession();
            String function = this.request.getParameter("function");
            String requestLoginId = this.request.getParameter("loginID");
            String loginIdInSession = (String) session.getAttribute("loginID");

            requestScope("loginID_this_page", requestLoginId);
            requestScope("loginIdInSession", loginIdInSession);

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


            //twitter連携関連
            Integer see_twitter_success = (Integer) session.getAttribute("see_twitter_success");
            Integer see_twitter_release_success = (Integer) session.getAttribute("see_twitter_release_success");

            if(see_twitter_success != null){
                if(0 == see_twitter_success.intValue()){//まだ一度も連携完了確認をしていない状態でユーザーページを見ていたら
                    this.request.getSession().setAttribute("see_twitter_success", 1);
                }else{
                    this.request.getSession().setAttribute("twitter_success", 0);
                }
            }

            if(see_twitter_release_success != null){
                if(0 == see_twitter_release_success.intValue()){//まだ一度も連携解除完了確認をしていない状態でユーザーページを見ていたら
                    this.request.getSession().setAttribute("see_twitter_release_success", 1);
                }else{
                    this.request.getSession().setAttribute("twitter_release_success", 0);
                }
            }

            //カテゴリごとのアイテム数の取得
            Map<String, Integer> countByCategoryMap = Memcache.get(Consts.CountByCategoryMap_KEY);
            if(countByCategoryMap == null){
                countByCategoryMap = UtilityMethods.getCountByCategory();
                Memcache.put(Consts.CountByCategoryMap_KEY, countByCategoryMap);
            }
            requestScope("countByCategoryMap", countByCategoryMap);


            //各種ページへの遷移処理
            if("top".equals(function)){
                UserTopService service = new UserTopService();
                User user = service.getUser(requestLoginId);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }


                List<String> montageImageList = Memcache.get(Consts.MontageImageList_KEY + user.getLoginID());
                if(montageImageList == null){
                    montageImageList = service.getRandomImageUrlForUser(user);
                    Memcache.put(Consts.MontageImageList_KEY + user.getLoginID(), montageImageList);
                }

                //get activity info this user
                UserTopService topService = new UserTopService();
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


                List<Object> itemListForUserTop = Memcache.get(Consts.ItemListForUserTop_KEY + user.getLoginID());
                List<Item> itemList = null;
                List<String> itemKeyList = null;
                List<String> categoryNameList = null;
                List<String> imageUrlList = null;

                if(itemListForUserTop == null){
                    itemList = service.getUserTopItemList(requestLoginId);
                    itemKeyList = service.getItemKeyList(itemList);
                    categoryNameList = service.getCategoryNameList(itemList);
                    imageUrlList = service.getImageUrlList(itemList);

                    itemListForUserTop = new ArrayList<Object>();
                    itemListForUserTop.add(itemList);
                    itemListForUserTop.add(itemKeyList);
                    itemListForUserTop.add(categoryNameList);
                    itemListForUserTop.add(imageUrlList);
                    Memcache.put(Consts.ItemListForUserTop_KEY + user.getLoginID(), itemListForUserTop);
                }else{
                    itemList = (List<Item>) itemListForUserTop.get(0);
                    itemKeyList = (List<String>) itemListForUserTop.get(1);
                    categoryNameList = (List<String>) itemListForUserTop.get(2);
                    imageUrlList = (List<String>) itemListForUserTop.get(3);
                }

                int itemCount = user.getItemRefs().getModelList().size();

                int pageCount = 0;
                if((itemCount % 5) == 0){
                    pageCount = (itemCount / 5);
                }else{
                    pageCount = (itemCount / 5) + 1;
                }
                requestScope("itemList_top", itemList);
                requestScope("montageImageList", montageImageList);
                requestScope("itemKeyList", itemKeyList);
                requestScope("categoryNameList", categoryNameList);
                requestScope("imageUrlList", imageUrlList);
                requestScope("itemCount", itemCount);
                requestScope("itemCount_top", itemCount);
                requestScope("pageCount", pageCount);
                requestScope("currentPage", 1);
                return forward("/user.jsp");
            }else if("post".equals(function)){
                UserTopService service = new UserTopService();
                User user = service.getUser(loginIdInSession);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }
                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                return forward("/post.jsp");
            }else if("page".equals(function)){
                UserTopService service = new UserTopService();
                String requestPage = this.request.getParameter("pageCount");
                User user = service.getUser(requestLoginId);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }

                List<Item> itemList = service.getUserPageItemList(requestLoginId, Integer.parseInt(requestPage));
                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                List<String> itemKeyList = service.getItemKeyList(itemList);
                List<String> categoryNameList = service.getCategoryNameList(itemList);
                List<String> imageUrlList = service.getImageUrlList(itemList);

                int itemCount = user.getItemRefs().getModelList().size();

                int pageCount = 0;
                if((itemCount % 5) == 0){
                    pageCount = (itemCount / 5);
                }else{
                    pageCount = (itemCount / 5) + 1;
                }
                requestScope("itemList_top", itemList);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                requestScope("itemKeyList", itemKeyList);
                requestScope("categoryNameList", categoryNameList);
                requestScope("imageUrlList", imageUrlList);
                requestScope("itemCount", itemCount);
                requestScope("itemCount_top", itemCount - (Integer.parseInt(requestPage) - 1) * 5);
                requestScope("pageCount", pageCount);
                requestScope("currentPage", Integer.parseInt(requestPage));
                return forward("/page.jsp");
            }else if("item".equals(function)){
                String itemKey = this.request.getParameter("itemKey");
                UserTopService service = new UserTopService();
                User user = service.getUser(requestLoginId);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }

                List<Item> itemList = service.getEachItem(itemKey);
                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                List<String> itemKeyList = service.getItemKeyList(itemList);
                List<String> categoryNameList = service.getCategoryNameList(itemList);
                List<String> imageUrlList = service.getImageUrlList(itemList);
                requestScope("itemList_size", itemList.size());
                requestScope("itemList_top", itemList);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                requestScope("itemKeyList", itemKeyList);
                requestScope("categoryNameList", categoryNameList);
                requestScope("imageUrlList", imageUrlList);
                return forward("/item.jsp");
            }else if("list".equals(function)){
                String pageNo = this.request.getParameter("pageNo");
                UserTopService service = new UserTopService();
                User user = service.getUser(requestLoginId);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }

                List<Item> itemList = service.getItemForList(requestLoginId, Integer.parseInt(pageNo));
                List<String> categoryNameList = service.getCategoryNameList(itemList);
                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                List<String> itemKeyList = service.getItemKeyList(itemList);

                int itemCount = user.getItemRefs().getModelList().size();

                int pageCount = 0;
                if((itemCount % 30) == 0){
                    pageCount = (itemCount / 30);
                }else{
                    pageCount = (itemCount / 30) + 1;
                }
                requestScope("itemList_list", itemList);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                requestScope("itemKeyList", itemKeyList);
                requestScope("categoryNameList", categoryNameList);
                requestScope("itemCount", itemCount);
                requestScope("itemCount_top", itemCount - (Integer.parseInt(pageNo) - 1) * 30);
                requestScope("pageCount", pageCount);
                requestScope("currentPage", Integer.parseInt(pageNo));
                return forward("/list.jsp");
            }else if("settings".equals(function)){
                requestScope("settingsChangeSuccess", "");
                UserTopService service = new UserTopService();
                User user = service.getUser(loginIdInSession);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }
                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                return forward("/settings.jsp");
            }else if("resign".equals(function)){
                UserTopService service = new UserTopService();
                User user = service.getUser(loginIdInSession);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }
                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                return forward("/resign.jsp");
            }else if("sendMessage".equals(function)){
                UserTopService service = new UserTopService();
                User user = service.getUser(requestLoginId);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }

                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                return forward("/send.jsp");
            }else if("message".equals(function)){
                UserTopService service = new UserTopService();
                User user = service.getUser(loginIdInSession);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }


                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                ArrayList<ArrayList<String>> messageList = service.getMessageData(user);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                requestScope("messageLabelList", messageList.get(0));
                requestScope("messageValueList", messageList.get(1));
                return forward("/read.jsp");
            }else if("edit".equals(function)){
                String itemKey = this.request.getParameter("itemKey");
                UserTopService service = new UserTopService();
                User user = service.getUser(loginIdInSession);
                Map<String, Object> userMap = service.getUser(user);
                requestScope("user", userMap);

                if(user == null){
                    return forward("/user.jsp");
                }
                List<Activity> activityList = service.getActivityList(requestLoginId);
                List<Map<String, Object>> activityUserList = service.getActivityUserList(activityList, user);
                requestScope("activityList", activityList);
                requestScope("activityUserList", activityUserList);
                requestScope("editTarget", service.getEditTarget(itemKey));
                return forward("/edit.jsp");
            }
        }catch(Exception e){
            throw UtilityMethods.sendErrorMail(this.getClass().getName(), e);
        }
        return forward("/user.jsp");
    }
}
