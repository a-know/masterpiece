package com.aknow.masterpiece.controller;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;

import com.aknow.masterpiece.service.DeleteItemService;
import com.aknow.masterpiece.util.Consts;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class DeleteItemController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		this.response.setContentType(Consts.CONTENT_TYPE);

		//入力情報を取得
		String keyValue = this.request.getParameter("key");

		Key key = KeyFactory.stringToKey(keyValue);


		DeleteItemService service = new DeleteItemService();

		JSON.encode(service.deleteItem(key), this.response.getOutputStream());


		return null;
	}
}
