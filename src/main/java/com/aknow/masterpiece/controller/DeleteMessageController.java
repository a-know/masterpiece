package com.aknow.masterpiece.controller;

import java.util.HashMap;
import java.util.Map;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;

import com.aknow.masterpiece.service.DeleteMessageService;
import com.aknow.masterpiece.util.Consts;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DeleteMessageController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {
		this.response.setContentType(Consts.CONTENT_TYPE);

		//入力情報を取得
		String keyValue = asString("key");
		String loginIdInSession = sessionScope("loginID");

		Key key = KeyFactory.stringToKey(keyValue);

		DeleteMessageService service = new DeleteMessageService();

		Map<String, String> map = new HashMap<String, String>();
		service.deleteMessage(key);
		map.put("loginID", loginIdInSession);


		JSON.encode(map, this.response.getOutputStream());


		return null;
	}
}
