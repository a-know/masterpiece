package com.aknow.masterpiece.service;

import java.util.HashMap;
import java.util.Map;

import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;

import com.google.appengine.api.datastore.Key;


public class DeleteItemService {

    @SuppressWarnings("static-method")
    public Map<String, Object> deleteItem(Key key){
        Map<String, Object> map = new HashMap<String, Object>();

        @SuppressWarnings("deprecation")
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();

        Item item = gtx.get(Item.class, key);
        User user = item.getUserRef().getModel();

        int userStarCount = user.getStarCount().intValue();
        int userItemCount = user.getPostCount().intValue();
        int starCount = item.getStarCount().intValue();

        //sub
        userStarCount = userStarCount - starCount;
        userItemCount--;

        //set
        user.setStarCount(Integer.valueOf(userStarCount));
        user.setPostCount(Integer.valueOf(userItemCount));

        //delete, update
        gtx.delete(key);
        gtx.put(user);
        gtx.commit();
        Memcache.delete(Consts.CountByCategoryMap_KEY);
        Memcache.delete(Consts.SlideDataList_KEY);
        Memcache.delete(Consts.LatestDataList_KEY);
        Memcache.delete(Consts.MontageImageList_KEY + user.getLoginID());
        Memcache.delete(Consts.ItemListForUserTop_KEY + user.getLoginID());

        //return
        map.put("errorCode", "0");
        map.put("loginID", user.getLoginID());
        return map;
    }
}
