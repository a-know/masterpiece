package com.aknow.masterpiece.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aknow.masterpiece.meta.ItemMeta;
import com.aknow.masterpiece.meta.MessageMeta;
import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.Activity;
import com.aknow.masterpiece.model.Item;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
import com.google.appengine.api.mail.MailService.Message;


public class UtilityMethods {
    public static String getCategoryName(String categoryCode){
        if("01".equals(categoryCode)){
            return "コンピュータ";
        }else if("02".equals(categoryCode)){
            return "デジタルガジェット";
        }else if("03".equals(categoryCode)){
            return "携帯電話";
        }else if("04".equals(categoryCode)){
            return "電化製品";
        }else if("05".equals(categoryCode)){
            return "オーディオ機器";
        }else if("06".equals(categoryCode)){
            return "カメラ";
        }else if("07".equals(categoryCode)){
            return "生活雑貨";
        }else if("08".equals(categoryCode)){
            return "文房具";
        }else if("09".equals(categoryCode)){
            return "雑貨";
        }else if("10".equals(categoryCode)){
            return "CD/DVD類";
        }else if("11".equals(categoryCode)){
            return "書籍";
        }else if("12".equals(categoryCode)){
            return "おもちゃ/ホビー";
        }else if("13".equals(categoryCode)){
            return "模型/プラモデル";
        }else if("14".equals(categoryCode)){
            return "楽器";
        }else if("15".equals(categoryCode)){
            return "アート";
        }else if("16".equals(categoryCode)){
            return "アンティーク/コレクション";
        }else if("17".equals(categoryCode)){
            return "ファッション";
        }else if("18".equals(categoryCode)){
            return "アクセサリー";
        }else if("19".equals(categoryCode)){
            return "服飾雑貨/小物";
        }else if("20".equals(categoryCode)){
            return "家具/インテリア";
        }else if("21".equals(categoryCode)){
            return "時計";
        }else if("22".equals(categoryCode)){
            return "スポーツ用品";
        }else if("23".equals(categoryCode)){
            return "自動車/バイク";
        }else if("24".equals(categoryCode)){
            return "食品/飲料";
        }else if("25".equals(categoryCode)){
            return "ペット/生き物";
        }else if("26".equals(categoryCode)){
            return "植物";
        }else{
            return "その他";
        }
    }

    @SuppressWarnings("deprecation")
	public static Map<String, Object> getUser(String loginID){
        Map<String, Object> userMap = new HashMap<String, Object>();

        UserMeta meta = new UserMeta();
        User user = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asSingle();

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

    public static String makeStarString(int starCount){
        int tenStar = starCount / 10;
        int oneStar = starCount % 10;

        StringBuffer star_string = new StringBuffer();
        for(int i = 0; i < tenStar; i++){
            star_string.append("☆");
        }
        for(int i = 0; i < oneStar; i++){
            star_string.append("★");
        }

        return star_string.toString();
    }

    /**
     * 引数として受けたblobkeyでblobstore上のデータを削除する。
     *
     * @param BlobKey
     *            削除対象のblobkey
     * @return boolean
     *            false : 削除に失敗
     *            true  : 削除に成功
     */
    public static boolean deleteBlob(BlobKey blobKey){

        if(blobKey == null || blobKey.equals("")) return true;

        // Get a file service
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        try{
            blobstoreService.delete(blobKey);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @SuppressWarnings("static-method")
    public List<Map<String, Object>> getActivityUserList(List<Activity> activityList){
        List<Map<String, Object>> activityUserList = new ArrayList<Map<String, Object>>();
        for(Activity a : activityList){
            activityUserList.add(UtilityMethods.getUser(a.getLoginID()));
        }
        return activityUserList;
    }

    public static Integer getUnreadMessageNumber(String loginID){
        MessageMeta meta = new MessageMeta();
        int count = Datastore.query(meta).filter(meta.to.equal(loginID), meta.isUnread.equal(Boolean.TRUE)).count();
        return Integer.valueOf(count);
    }

    public static Map<String, Integer> getCountByCategory(){
        Map<String, Integer> map = countByCategoryMap();

        ItemMeta meta = new ItemMeta();
        List<Item> itemList = Datastore.query(meta).asList();

        Integer currentCount = null;

        for(Item i : itemList){
            currentCount = map.get(i.getCategoryCode());
            currentCount = Integer.valueOf(currentCount.intValue() + 1);
            map.put(i.getCategoryCode(), currentCount);
        }

        return map;
    }

    private static Map<String, Integer> countByCategoryMap(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("01", Integer.valueOf(0));
        map.put("02", Integer.valueOf(0));
        map.put("03", Integer.valueOf(0));
        map.put("04", Integer.valueOf(0));
        map.put("05", Integer.valueOf(0));
        map.put("06", Integer.valueOf(0));
        map.put("07", Integer.valueOf(0));
        map.put("08", Integer.valueOf(0));
        map.put("09", Integer.valueOf(0));
        map.put("10", Integer.valueOf(0));
        map.put("11", Integer.valueOf(0));
        map.put("12", Integer.valueOf(0));
        map.put("13", Integer.valueOf(0));
        map.put("14", Integer.valueOf(0));
        map.put("15", Integer.valueOf(0));
        map.put("16", Integer.valueOf(0));
        map.put("17", Integer.valueOf(0));
        map.put("18", Integer.valueOf(0));
        map.put("19", Integer.valueOf(0));
        map.put("20", Integer.valueOf(0));
        map.put("21", Integer.valueOf(0));
        map.put("22", Integer.valueOf(0));
        map.put("23", Integer.valueOf(0));
        map.put("24", Integer.valueOf(0));
        map.put("25", Integer.valueOf(0));
        map.put("26", Integer.valueOf(0));
        map.put("99", Integer.valueOf(0));

        return map;
    }
    
    public static Exception sendErrorMail(String className, Exception e) throws IOException{
        Message message = new Message();
        message.setSender(PrivateConsts.ERROR_MAIL_SENDER);
        message.setTo(PrivateConsts.ERROR_MAIL_TO);

        message.setSubject(className);
        message.setTextBody(e.toString() + "\n" + e.getMessage());

        MailService mailService = MailServiceFactory.getMailService();
        mailService.send(message);
        
        return e;
    }
}
