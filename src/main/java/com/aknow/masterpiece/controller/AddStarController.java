package com.aknow.masterpiece.controller;

import java.io.IOException;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import org.slim3.controller.Navigation;

import com.aknow.masterpiece.service.AddStarService;
import com.aknow.masterpiece.util.Consts;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class AddStarController extends BaseController {

    @Override
    public Navigation runImpl() throws JSONException, IOException {

        this.response.setContentType(Consts.CONTENT_TYPE);

        //入力情報を取得
        String keyValue = this.request.getParameter("key");

        Key key = KeyFactory.stringToKey(keyValue);

        AddStarService service = new AddStarService();
        JSON.encode(service.addStar(key), this.response.getOutputStream());

        return null;
    }
}
