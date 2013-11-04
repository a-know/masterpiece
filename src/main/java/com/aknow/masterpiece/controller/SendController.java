package com.aknow.masterpiece.controller;

import java.util.HashMap;
import java.util.Map;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.service.SendMessageService;
import com.aknow.masterpiece.util.Consts;

public class SendController extends BaseController {

	@Override
	public Navigation runImpl() throws Exception {

		this.response.setContentType(Consts.CONTENT_TYPE);

		//入力情報を取得
		String to = this.request.getParameter("to");
		String sender = this.request.getParameter("sender");
		String content = this.request.getParameter("content");

		SendMessageService service = new SendMessageService();
		service.sendMessage(to, sender, content);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCode", "0");
		map.put("to", to);
		map.put("sender", sender);

		//delete memcache
		Memcache.delete(Consts.UnreadMessageNumber_KEY + to);

		JSON.encode(map, this.response.getOutputStream());

		return null;
	}
}
