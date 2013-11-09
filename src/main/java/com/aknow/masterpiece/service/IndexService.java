package com.aknow.masterpiece.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aknow.masterpiece.meta.ActivityMeta;
import com.aknow.masterpiece.meta.ItemMeta;
import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;
import org.slim3.util.BeanUtil;

import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;


public class IndexService {
    @SuppressWarnings({ "static-method", "deprecation" })
    public List<Map<String, Object>> getSlideList(){
        ItemMeta meta = new ItemMeta();
        List<Item> list = Datastore.query(meta).sort(meta.starCount.desc).limit(5).asList();


        ImagesService imagesService = null;
        List<Map<String, Object>> slidelist = new ArrayList<Map<String, Object>>();

        for(Item i : list){
            String url = Memcache.get(Consts.ImageUrl_KEY + i.getBlobKey().getKeyString());
            if(url == null){
                imagesService = ImagesServiceFactory.getImagesService();
                url = imagesService.getServingUrl(i.getBlobKey());
                Memcache.put(Consts.ImageUrl_KEY + i.getBlobKey().getKeyString(), url);
            }
            i.setImageUrl(url);
            Map<String, Object> map = new HashMap<String, Object>();
            BeanUtil.copy(i, map);
            map.put("categoryName", UtilityMethods.getCategoryName(i.getCategoryCode()));
            map.put("itemKey", KeyFactory.keyToString(i.getKey()));
            String replaced = i.getComment().replaceAll("<br>", "");
            if(replaced.length() < 50){
                map.put("comment", replaced);
            }else{
                map.put("comment", replaced.substring(0, 50) + "...");
            }
            slidelist.add(map);
        }

        return slidelist;
    }

    @SuppressWarnings({ "static-method", "deprecation" })
    public List<Map<String, Object>> getLatestList(){
        ItemMeta meta = new ItemMeta();
        List<Item> list = Datastore.query(meta).sort(meta.postDate.desc).limit(6).asList();

        ImagesService imagesService = null;
        List<Map<String, Object>> latestList = new ArrayList<Map<String, Object>>();

        for(Item i : list){
            imagesService = ImagesServiceFactory.getImagesService();

            String url = Memcache.get(Consts.ImageUrl288_KEY + i.getBlobKey().getKeyString());
            if(url == null){
                imagesService = ImagesServiceFactory.getImagesService();
                url = imagesService.getServingUrl(i.getBlobKey(), 288, false);
                Memcache.put(Consts.ImageUrl288_KEY + i.getBlobKey().getKeyString(), url);
            }
            i.setImageUrl(url);

            Map<String, Object> map = new HashMap<String, Object>();
            BeanUtil.copy(i, map);
            map.put("categoryName", UtilityMethods.getCategoryName(i.getCategoryCode()));
            map.put("itemKey", KeyFactory.keyToString(i.getKey()));
            String replaced = i.getComment().replaceAll("<br>", "");
            if(replaced.length() < 70){
                map.put("comment", replaced);
            }else{
                map.put("comment", replaced.substring(0, 70) + "...");
            }
            latestList.add(map);
        }

        return latestList;
    }

    @SuppressWarnings("static-method")
    public List<Activity> getActivityList(){
        ActivityMeta meta = new ActivityMeta();
        List<Activity> list = Datastore.query(meta).sort(meta.activityDate.desc).limit(10).asList();
        return list;
    }

    @SuppressWarnings("static-method")
    public List<Map<String, Object>> getActivityUserList(List<Activity> activityList){
        List<Map<String, Object>> activityUserList = new ArrayList<Map<String, Object>>();
        for(Activity a : activityList){
            activityUserList.add(UtilityMethods.getUser(a.getLoginID()));
        }
        return activityUserList;
    }
}
