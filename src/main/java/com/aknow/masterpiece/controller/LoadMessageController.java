package com.aknow.masterpiece.controller;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;

import com.aknow.masterpiece.service.LoadMessageService;
import com.aknow.masterpiece.util.Consts;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class LoadMessageController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		this.response.setContentType(Consts.CONTENT_TYPE);

		//入力情報を取得
		String keyValue = this.request.getParameter("key");

		Key key = KeyFactory.stringToKey(keyValue);

		LoadMessageService service = new LoadMessageService();

		JSON.encode(service.loadMessage(key), this.response.getOutputStream());

		return null;
	}
}