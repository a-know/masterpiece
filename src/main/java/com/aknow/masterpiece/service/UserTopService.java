package com.aknow.masterpiece.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aknow.masterpiece.meta.ActivityMeta;
import com.aknow.masterpiece.meta.ItemMeta;
import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.Message;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;
import org.slim3.memcache.Memcache;
import org.slim3.util.BeanUtil;
import org.slim3.util.DateUtil;

import com.aknow.masterpiece.util.Consts;
import com.aknow.masterpiece.util.UtilityMethods;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;


public class UserTopService {

    public User getUser(String loginID){
        UserMeta meta = new UserMeta();
        User user = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asSingle();
        return user;
    }
    
    public List<Item> getUserTopItemList(String loginID){
        ItemMeta meta = new ItemMeta();
        List<Item> list = Datastore.query(meta).filter(meta.loginID.equal(loginID)).sort(meta.postDate.desc).limit(5).asList();
        return list;
    }

    public List<Activity> getActivityList(String loginID){
        ActivityMeta meta = new ActivityMeta();
        List<Activity> list = Datastore.query(meta).filter(meta.loginID.equal(loginID)).sort(meta.activityDate.desc).limit(5).asList();
        return list;
    }

    public List<Map<String, Object>> getActivityUserList(List<Activity> activityList, User user){
        List<Map<String, Object>> activityUserList = new ArrayList<Map<String, Object>>();
        for(Activity a : activityList){
            if(a.getLoginID().equals(user.getLoginID())){
                activityUserList.add(this.getUser(user));
            }else{
                activityUserList.add(UtilityMethods.getUser(a.getLoginID()));
            }
        }
        return activityUserList;
    }

    
    public List<String> getItemKeyList(List<Item> list){
        List<String> itemKeyList = new ArrayList<String>();
        for(Item e : list){
            itemKeyList.add(KeyFactory.keyToString(e.getKey()));
        }
        return itemKeyList;
    }

    
    public List<String> getCategoryNameList(List<Item> list){
        List<String> nameList = new ArrayList<String>();
        for(Item e : list){
            nameList.add(UtilityMethods.getCategoryName(e.getCategoryCode()));
        }
        return nameList;
    }

    
    @SuppressWarnings("deprecation")
	public List<String> getImageUrlList(List<Item> list){
        List<String> imageList = new ArrayList<String>();
        ImagesService imagesService = null;
        for(Item e : list){
            imagesService = ImagesServiceFactory.getImagesService();

            String url = Memcache.get(Consts.ImageUrl512_KEY + e.getBlobKey().getKeyString());
            if(url == null){
                imagesService = ImagesServiceFactory.getImagesService();
                url = imagesService.getServingUrl(e.getBlobKey(), 512, false);
                Memcache.put(Consts.ImageUrl512_KEY + e.getBlobKey().getKeyString(), url);
            }

            imageList.add(url);
        }
        return imageList;
    }

    
    public List<Item> getUserPageItemList(String loginID, int pageCount){
        List<Item> itemList = new ArrayList<Item>();
        ItemMeta meta = new ItemMeta();
        List<Key> keyList = Datastore.query(meta).filter(meta.loginID.equal(loginID)).sort(meta.postDate.desc).asKeyList();
        int start = (pageCount - 1) * 5;
        int count = 0;
        for(int i = start; i < keyList.size(); i++){
            itemList.add(Datastore.get(Item.class, keyList.get(i)));
            count++;
            if(count == 5) break;
        }
        return itemList;
    }

    
    public List<Item> getEachItem(String itemKey){
        List<Item> itemList = new ArrayList<Item>();

        Key key = KeyFactory.stringToKey(itemKey);
        Item item = null;

        try{
            item = Datastore.get(Item.class, key);
        }catch(EntityNotFoundRuntimeException e){
            return itemList;
        }

        itemList.add(item);

        return itemList;
    }

    
    public List<Item> getItemForList(String loginID, int pageNo){
        List<Item> itemList = new ArrayList<Item>();
        ItemMeta meta = new ItemMeta();
        List<Key> keyList = Datastore.query(meta).filter(meta.loginID.equal(loginID)).sort(meta.postDate.desc).asKeyList();
        int start = (pageNo - 1) * 30;
        int count = 0;
        for(int i = start; i < keyList.size(); i++){
            itemList.add(Datastore.get(Item.class, keyList.get(i)));
            count++;
            if(count == 30) break;
        }
        return itemList;
    }

    
    public ArrayList<ArrayList<String>> getMessageData(User user){

        ArrayList<String> messageLabelList = new ArrayList<String>();
        ArrayList<String> messageValueList = new ArrayList<String>();

        List<Message> list = user.getMessageRefs().getModelList();

        StringBuffer bf = null;
        String pattern = "yyyy/MM/dd HH:mm";

        for(Message e : list){
            bf = new StringBuffer();
            bf.append(e.getSender());
            bf.append("さんから（");
            bf.append(DateUtil.toString(e.getSendDate(),pattern));
            bf.append(" に送信）");
            messageLabelList.add(bf.toString());
            messageValueList.add(KeyFactory.keyToString(e.getKey()));
        }

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result.add(messageLabelList);
        result.add(messageValueList);

        return result;
    }

    
    @SuppressWarnings("deprecation")
	public Map<String, Object> getUser(User pUser){
        Map<String, Object> userMap = new HashMap<String, Object>();

        User user = pUser;

        if(user == null){
            user = new User();
            user.setLoginID("");
            BeanUtil.copy(user, userMap);
            return userMap;
        }

        if(user.getIconFileBlobKey() == null){
            user.setImageUrl("/images/default_icon.png");
        }else{
            String userImageUrl = Memcache.get(Consts.UserImageUrl_KEY + user.getLoginID());
            if(userImageUrl == null){
                ImagesService imagesService = ImagesServiceFactory.getImagesService();
                userImageUrl = imagesService.getServingUrl(user.getIconFileBlobKey(), 48, false);
                Memcache.put(Consts.UserImageUrl_KEY + user.getLoginID(), userImageUrl);
            }
            user.setImageUrl(userImageUrl);
        }

        BeanUtil.copy(user, userMap);

        return userMap;
    }

    /**
     * ランダムでそのユーザーの登録アイテムから５つを抽出し、その画像URLリストを返す。
     * 登録数が５以下のときは、登録されているすべてのアイテムの画像リストを返す。
     * @param user
     * @return
     */
    
    @SuppressWarnings("deprecation")
	public List<String> getRandomImageUrlForUser(User user){
        ImagesService imagesService = null;
        List<String> urlList = new ArrayList<String>();

        List<Item> itemList = user.getItemRefs().getModelList();

        if(itemList.size() < 9){
            for(Item i : itemList){
                imagesService = ImagesServiceFactory.getImagesService();

                String url = Memcache.get(Consts.ImageUrl320_KEY + i.getBlobKey().getKeyString());
                if(url == null){
                    url = imagesService.getServingUrl(i.getBlobKey(), 320, true);
                    Memcache.put(Consts.ImageUrl320_KEY + i.getBlobKey().getKeyString(), url);
                }
                urlList.add(url);
            }
        }else{
            int limit = 8;
            List<String> randomList = new ArrayList<String>();
            while(limit > 0){
                int random = (int) Math.floor(Math.random() * itemList.size());
                while(randomList.contains(String.valueOf(random))){
                    random = (int) Math.floor(Math.random() * itemList.size());
                }
                randomList.add(String.valueOf(random));
                limit--;
            }
            for(String s : randomList){
                Item item = itemList.get(Integer.parseInt(s));
                imagesService = ImagesServiceFactory.getImagesService();

                String url = Memcache.get(Consts.ImageUrl320_KEY + item.getBlobKey().getKeyString());
                if(url == null){
                    url = imagesService.getServingUrl(item.getBlobKey(), 320, true);
                    Memcache.put(Consts.ImageUrl320_KEY + item.getBlobKey().getKeyString(), url);
                }

                urlList.add(imagesService.getServingUrl(item.getBlobKey(), 320, true));
            }
        }

        return urlList;
    }

    
    public Map<String, Object> getEditTarget(String itemKey){
        Map<String, Object> map = new HashMap<String, Object>();
        Key key = KeyFactory.stringToKey(itemKey);
        Item item = Datastore.get(Item.class, key);
        BeanUtil.copy(item, map);
        map.put("keyString",KeyFactory.keyToString(item.getKey()));
        map.put("categoryName", UtilityMethods.getCategoryName(item.getCategoryCode()));
        map.put("replaceComments", item.getComment().replaceAll("<br>", "\n"));
        return map;
    }
}
