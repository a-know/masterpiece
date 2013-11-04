package com.aknow.masterpiece.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import com.aknow.masterpiece.util.UtilityMethods;

public class TwitterOauthController extends Controller {

    @Override
    public Navigation run() throws Exception {

        try{
            Twitter twitter = new TwitterFactory().getInstance();

            RequestToken reqToken = twitter.getOAuthRequestToken("http://master-piece.appspot.com/callback");

            //sessionにオブジェクトを格納
            this.request.getSession().setAttribute("twitter", twitter);
            this.request.getSession().setAttribute("reqToken", reqToken);

            this.response.sendRedirect(reqToken.getAuthenticationURL());
        }catch(Exception e){
            throw UtilityMethods.sendErrorMail(this.getClass().getName(), e);
        }

        return null;
    }
}
