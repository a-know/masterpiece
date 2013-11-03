package com.aknow.masterpiece.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.aknow.masterpiece.meta.ItemMeta;
import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.TwitterUtil;
import com.aknow.masterpiece.util.UtilityMethods;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


public class PostItemService {

    public Item postItem(String loginID, String postTargetLoginID, Map<String, BlobKey> blobs, String category, String name, String comment, String url, String isTweet, String tweetContent){

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));

        if(!loginID.equals(postTargetLoginID)){//TODO nullpoのときがある
            return null;
        }

        @SuppressWarnings("deprecation")
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();

        UserMeta meta = new UserMeta();
        List<Key> keyList = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asKeyList();

        User user = gtx.get(User.class, keyList.get(0));

        Item item = new Item();
        item.setName(replaceCharacters(name));
        item.setCategoryCode(category);
        item.setComment(replaceCharacters(comment));
        item.setPostDate(calendar.getTime());
        item.setBlobKey(blobs.get("postFileInput"));
        item.setUrl(url);
        item.setStarCount(new Integer(0));
        item.setStarString("");
        item.getUserRef().setModel(user);
        item.setLoginID(loginID);
        gtx.put(item);

        Activity activity = new Activity();
        activity.setActivityCode("1");
        activity.setActivityDate(calendar.getTime());
        HashMap<Object, Object> info = new HashMap<Object, Object>();
        info.put("loginID", loginID);
        info.put("name", name);
        info.put("itemKey", KeyFactory.keyToString(item.getKey()));
        info.put("category", UtilityMethods.getCategoryName(category));
        activity.setActivityInfo(info);
        activity.setLoginID(loginID);
        activity.getUserRef().setModel(user);
        gtx.put(activity);

        Integer currentCount = user.getPostCount();
        user.setPostCount(new Integer(currentCount.intValue() + 1));

        gtx.put(user);

        //commit
        gtx.commit();
        Memcache.delete(Consts.AllActivityData_KEY);
        Memcache.delete(Consts.ActivityDatasByUser_KEY + user.getLoginID());

        if("on".equals(isTweet)){
            if(user.getTwitterAccessToken() != null && !user.getTwitterAccessToken().equals("")){
                String content = tweetContent.replaceAll("@+", KeyFactory.keyToString(item.getKey()));
                new TwitterUtil().doTweet(content, user);
            }
        }

        return item;
    }

    @SuppressWarnings("static-method")
    public String replaceCharacters(String target){

        String replace = null;

        replace = target.replaceAll("<", "&lt;");
        replace = replace.replaceAll(">", "&gt;");
        replace = replace.replaceAll("\n", "<br>");

        return replace;
    }

    @SuppressWarnings("static-method")
    public Integer getItemNo(String loginID){
        ItemMeta meta = new ItemMeta();
        int count = Datastore.query(meta).filter(meta.loginID.equal(loginID)).count();
        count++;
        return Integer.valueOf(count);
    }
}
