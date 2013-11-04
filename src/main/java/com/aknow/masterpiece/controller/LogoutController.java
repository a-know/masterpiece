package com.aknow.masterpiece.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

public class LogoutController extends BaseController {

    @Override
    public Navigation runImpl() throws Exception {
        HttpSession session = this.request.getSession(true);
        session.setAttribute("loginID", "");
        session.setAttribute("logon", Boolean.FALSE);
        session.invalidate();

        //カテゴリごとのアイテム数の取得
        Map<String, Integer> countByCategoryMap = Memcache.get(Consts.CountByCategoryMap_KEY);
        if(countByCategoryMap == null){
            countByCategoryMap = UtilityMethods.getCountByCategory();
            Memcache.put(Consts.CountByCategoryMap_KEY, countByCategoryMap);
        }
        requestScope("countByCategoryMap", countByCategoryMap);

        return forward("/login.jsp");
    }
}
