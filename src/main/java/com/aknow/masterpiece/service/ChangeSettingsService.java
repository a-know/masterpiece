package com.aknow.masterpiece.service;

import java.util.Map;

import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Transaction;


public class ChangeSettingsService {

    public User changeSettings(String loginID, String targetID, String url, String introduction, Map<String, BlobKey> blobs){

        User user = null;
        UserMeta meta = new UserMeta();

        if(!loginID.equals(targetID)){//TODO nullpoのときがある
            return user;
        }

        Transaction tx = Datastore.beginTransaction();

        user = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asSingle();

        if(!"".equals(url)){
            user.setUrl(url);
        }
        if(!"".equals(introduction)){
            user.setIntroduction(replaceCharacters(introduction));
        }
        if(blobs != null){
            if(!blobs.isEmpty()){
                user.setIconFileBlobKey(blobs.get("iconFileInput"));
                Memcache.delete(Consts.UserImageUrl_KEY + user.getLoginID());
            }
        }

        Datastore.put(user);
        tx.commit();

        return user;
    }

    @SuppressWarnings("static-method")
    public String replaceCharacters(String target){

        //不要なタグの置き換え
        String replace = null;

        replace = target.replaceAll("<", "&lt;");
        replace = replace.replaceAll(">", "&gt;");
        replace = replace.replaceAll("\n", "");

        return replace;
    }
}
