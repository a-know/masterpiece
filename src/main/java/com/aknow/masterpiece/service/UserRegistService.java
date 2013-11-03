package com.aknow.masterpiece.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;


public class UserRegistService {
    @SuppressWarnings("static-method")
    public Map<String, Object> userRegist(String loginID, String password){

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));

        Map<String, Object> returnMap = new HashMap<String, Object>();

        UserMeta meta = new UserMeta();
        int count = Datastore.query(meta).filter(meta.loginID.equal(loginID)).count();

        if(count > 0){
            returnMap.put("errorCode", "1");
        }else{

            @SuppressWarnings("deprecation")
            GlobalTransaction gtx = Datastore.beginGlobalTransaction();

            User user = new User();
            user.setLoginID(loginID);
            user.setPassword(password);
            user.setPostCount(new Integer(0));
            user.setIntroduction("");
            user.setStarCount(new Integer(0));
            user.setTwitterAccessToken("");
            user.setTwitterAccessTokenSecret("");
            user.setUrl("");

            Activity activity = new Activity();
            activity.setActivityCode("0");
            activity.setActivityDate(calendar.getTime());
            HashMap<Object, Object> info = new HashMap<Object, Object>();
            info.put("loginID", loginID);
            activity.setActivityInfo(info);
            activity.setLoginID(loginID);
            activity.getUserRef().setModel(user);

            gtx.put(activity);
            gtx.put(user);

            gtx.commit();
            Memcache.delete(Consts.AllActivityData_KEY);
            Memcache.delete(Consts.ActivityDatasByUser_KEY + user.getLoginID());

            returnMap.put("errorCode", "0");//success
            returnMap.put("loginID", loginID);
        }

        return returnMap;
    }
}
