package com.aknow.masterpiece.controller;

import org.slim3.controller.Navigation;

import com.aknow.masterpiece.service.TwitterReleaseService;

public class TwitterReleaseController extends BaseController {

	@SuppressWarnings("boxing")
	@Override
	public Navigation runImpl() throws Exception {

		String loginID = (String) this.request.getSession().getAttribute("loginID");

		TwitterReleaseService service = new TwitterReleaseService();

		service.releaseTwitterTokens(loginID);

		this.request.getSession().setAttribute("twitter_release_success", 1);//twitter連携解除成功
		this.request.getSession().setAttribute("see_twitter_release_success", 0);//twitter連携解除成功を確認済みか？

		return redirect("/user/" + loginID);
	}
}
