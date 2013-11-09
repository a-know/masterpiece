package com.aknow.masterpiece.controller;

import org.slim3.controller.Navigation;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class TwitterOauthController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		Twitter twitter = new TwitterFactory().getInstance();

		RequestToken reqToken = twitter.getOAuthRequestToken("http://master-piece.appspot.com/callback");

		//sessionにオブジェクトを格納
		this.request.getSession().setAttribute("twitter", twitter);
		this.request.getSession().setAttribute("reqToken", reqToken);

		return redirect(reqToken.getAuthenticationURL());
	}
}
