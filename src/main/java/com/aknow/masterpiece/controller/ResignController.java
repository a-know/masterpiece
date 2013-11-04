package com.aknow.masterpiece.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.service.ResignService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

public class ResignController extends Controller {

    @Override
    public Navigation run() throws Exception {

        try{
            this.response.setContentType(Consts.CONTENT_TYPE);

            String loginID = this.request.getParameter("loginID");
            String loginIDinSession = this.request.getParameter("loginIDinSession");

            Integer unread = new Integer(0);
            if(loginIDinSession != null){
                //未読メッセージの件数を取得
                unread = Memcache.get(Consts.UnreadMessageNumber_KEY + loginIDinSession);
                if(unread == null){
                    unread = UtilityMethods.getUnreadMessageNumber(loginIDinSession);
                    Memcache.put(Consts.UnreadMessageNumber_KEY + loginIDinSession, unread);
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

            ResignService service = new ResignService();
            Map<String, Object> map = service.resign(loginID, loginIDinSession);

            if("0".equals(map.get("errorCode"))){
                HttpSession session = this.request.getSession();
                session.setAttribute("loginID", "");
                session.setAttribute("logon", Boolean.FALSE);
            }

            JSON.encode(map, this.response.getOutputStream());
        }catch(Exception e){
            throw UtilityMethods.sendErrorMail(this.getClass().getName(), e);
        }

        return null;
    }
}
