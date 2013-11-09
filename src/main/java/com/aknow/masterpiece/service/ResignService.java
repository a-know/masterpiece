package com.aknow.masterpiece.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import com.aknow.masterpiece.util.UtilityMethods;

import com.google.appengine.api.datastore.Key;


public class ResignService {
    @SuppressWarnings("static-method")
    public Map<String, Object> resign(String loginID, String loginIDinSession){
        Map<String, Object> map = new HashMap<String, Object>();

        if(!loginID.equals(loginIDinSession)){
            map.put("errorCode", "1");
            return map;
        }

        UserMeta meta = new UserMeta();
        @SuppressWarnings("deprecation")
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        List<Key> keyList = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asKeyList();

        User user = gtx.get(User.class, keyList.get(0));

        List<Activity> activityList = user.getActivityRefs().getModelList();
        for(Activity a : activityList){
            gtx.delete(a.getKey());
        }

        List<Item> itemlist = user.getItemRefs().getModelList();
        for(Item i : itemlist){
            UtilityMethods.deleteBlob(i.getBlobKey());
            gtx.delete(i.getKey());
        }

        UtilityMethods.deleteBlob(user.getIconFileBlobKey());
        gtx.delete(user.getKey());

        gtx.commit();

        map.put("errorCode", "0");

        return map;
    }
}
