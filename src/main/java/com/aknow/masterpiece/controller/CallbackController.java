package com.aknow.masterpiece.controller;

import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.User;

public class CallbackController extends BaseController {

	@SuppressWarnings("boxing")
	public Navigation runImpl() throws Exception {

		//セッションからtwitterオブジェクトとRequestTokenの取出
		AccessToken accessToken = null;
		Twitter twitter = (Twitter) this.request.getSession().getAttribute("twitter");
		RequestToken requestToken = (RequestToken) this.request.getSession().getAttribute("reqToken");
		String verifier = this.request.getParameter("oauth_verifier");
		String loginID = (String) this.request.getSession().getAttribute("loginID");

		//AccessTokenの取得
		try {
			accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
			this.request.getSession().removeAttribute("reqToken");
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		//AccessToken/Secret保存
		if(accessToken != null){
			UserMeta meta = new UserMeta();
			User user = Datastore.query(meta).filter(meta.loginID.equal(loginID)).asSingle();
			user.setTwitterAccessToken(accessToken.getToken());
			user.setTwitterAccessTokenSecret(accessToken.getTokenSecret());
			Datastore.put(user);
		}

		this.request.getSession().setAttribute("twitter_success", 1);//twitter連携成功
		this.request.getSession().setAttribute("see_twitter_success", 0);//twitter連携成功を確認済みか？


		this.response.sendRedirect("http://master-piece.appspot.com/user/" + loginID);


		return null;
	}
}
