package com.aknow.masterpiece.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.aknow.masterpiece.service.UserRegistService;
import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

public class UserRegistController extends Controller {

    @Override
    public Navigation run() throws Exception {

        try{
            this.response.setContentType(Consts.CONTENT_TYPE);

            String loginID = this.request.getParameter("loginID");
            String password = this.request.getParameter("password");

            UserRegistService service = new UserRegistService();
            Map<String, Object> map = service.userRegist(loginID, password);

            if("0".equals(map.get("errorCode"))){
                HttpSession session = this.request.getSession();
                session.setAttribute("loginID", loginID);
                session.setAttribute("logon", Boolean.TRUE);
            }

            JSON.encode(map, this.response.getOutputStream());
        }catch(Exception e){
            throw UtilityMethods.sendErrorMail(this.getClass().getName(), e);
        }

        return null;
    }
}
