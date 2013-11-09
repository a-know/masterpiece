package com.aknow.masterpiece.service;

import java.util.HashMap;
import java.util.Map;

import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;


public class LoginService {
    @SuppressWarnings("static-method")
    public Map<String, Object> login(String loginID, String password){
        Map<String, Object> returnMap = new HashMap<String, Object>();

        //入力されたログインＩＤ、パスワードで検索できるか？
        UserMeta meta = new UserMeta();
        User user = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asSingle();

        if(user == null){
            returnMap.put("errorCode", "1");
            returnMap.put("loginID", "");
        }else if(!password.equals(user.getPassword())){
            returnMap.put("errorCode", "1");
            returnMap.put("loginID", "");
        }else{
            returnMap.put("errorCode", "0");//success
            returnMap.put("loginID", loginID);
        }

        return returnMap;
    }
}
