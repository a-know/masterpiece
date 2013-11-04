package com.aknow.masterpiece.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.service.LoginService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

public class LoginController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		String loginID = this.request.getParameter("loginID-loginForm");
		String password = this.request.getParameter("password-loginForm");

		LoginService service = new LoginService();
		Map<String, Object> map = service.login(loginID, password);

		HttpSession session = this.request.getSession();
		if("0".equals(map.get("errorCode"))){
			session.setAttribute("loginError", "0");
			session.setAttribute("loginID", loginID);
			session.setAttribute("logon", Boolean.TRUE);
			Map<String, Object> user = UtilityMethods.getUser(loginID);
			requestScope("user", user);
		}else{
			session.setAttribute("loginError", "1");
		}

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