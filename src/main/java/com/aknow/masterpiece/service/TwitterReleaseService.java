package com.aknow.masterpiece.service;

import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;


public class TwitterReleaseService {
    @SuppressWarnings("static-method")
    public boolean releaseTwitterTokens(String loginID){

        UserMeta meta = new UserMeta();
        User user = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asSingle();
        user.setTwitterAccessToken("");
        user.setTwitterAccessTokenSecret("");
        Datastore.put(user);

        return true;
    }
}
