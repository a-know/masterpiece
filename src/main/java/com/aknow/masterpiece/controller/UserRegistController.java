package com.aknow.masterpiece.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;

import com.aknow.masterpiece.service.UserRegistService;
import com.aknow.masterpiece.util.Consts;

public class UserRegistController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		this.response.setContentType(Consts.CONTENT_TYPE);

		String loginID = asString("loginID");
		String password = asString("password");

		UserRegistService service = new UserRegistService();
		Map<String, Object> map = service.userRegist(loginID, password);

		if("0".equals(map.get("errorCode"))){
			HttpSession session = this.request.getSession();
			session.setAttribute("loginID", loginID);
			session.setAttribute("logon", Boolean.TRUE);
		}

		JSON.encode(map, this.response.getOutputStream());


		return null;
	}
}
