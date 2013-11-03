package com.aknow.masterpiece.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public class AddStarService {

    @SuppressWarnings("static-method")
    public Map<String, Object> addStar(Key key){
        Map<String, Object> map = new HashMap<String, Object>();

        @SuppressWarnings("deprecation")
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();

        Item item = gtx.get(Item.class, key);
        User user = item.getUserRef().getModel();

        int userStarCount = user.getStarCount().intValue();
        int starCount = item.getStarCount().intValue();
        String starString = item.getStarString();

        //add star
        userStarCount++;
        starCount++;
        starString = UtilityMethods.makeStarString(starCount);

        //set
        user.setStarCount(Integer.valueOf(userStarCount));
        item.setStarCount(Integer.valueOf(starCount));
        item.setStarString(starString);

        //activity
        Activity activity_item = null;
        Activity activity_user = null;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        if((starCount % 5) == 0){
            activity_item = new Activity();
            activity_item.setActivityCode("2");
            activity_item.setActivityDate(calendar.getTime());
            HashMap<Object, Object> info = new HashMap<Object, Object>();
            info.put("loginID", user.getLoginID());
            info.put("name", item.getName());
            info.put("itemKey", KeyFactory.keyToString(item.getKey()));
            info.put("category", UtilityMethods.getCategoryName(item.getCategoryCode()));
            info.put("starCount", new Integer(starCount));
            activity_item.setActivityInfo(info);
            activity_item.setLoginID(user.getLoginID());
            activity_item.getUserRef().setModel(user);
        }
        if((userStarCount % 10) == 0){
            activity_user = new Activity();
            activity_user.setActivityCode("3");
            activity_user.setActivityDate(calendar.getTime());
            HashMap<Object, Object> info = new HashMap<Object, Object>();
            info.put("loginID", user.getLoginID());
            info.put("userStarCount", new Integer(userStarCount));
            activity_user.setActivityInfo(info);
            activity_user.setLoginID(user.getLoginID());
            activity_user.getUserRef().setModel(user);
        }

        //update
        gtx.put(item);
        gtx.put(user);
        if(activity_item != null) {
            gtx.put(activity_item);
            Memcache.delete(Consts.AllActivityData_KEY);
            Memcache.delete(Consts.ActivityDatasByUser_KEY + user.getLoginID());
        }
        if(activity_user != null) {
            gtx.put(activity_user);
            Memcache.delete(Consts.AllActivityData_KEY);
            Memcache.delete(Consts.ActivityDatasByUser_KEY + user.getLoginID());
        }
        gtx.commit();
        Memcache.delete(Consts.SlideDataList_KEY);
        Memcache.delete(Consts.LatestDataList_KEY);

        //return
        map.put("starCount", Integer.valueOf(starCount));
        map.put("starString", starString);

        return map;
    }
}
