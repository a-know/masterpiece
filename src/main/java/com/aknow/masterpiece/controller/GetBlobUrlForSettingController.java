package com.aknow.masterpiece.controller;

import java.util.HashMap;
import java.util.Map;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.aknow.masterpiece.util.Consts;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class GetBlobUrlForSettingController extends Controller {

    @Override
    public Navigation run() throws Exception {
        this.response.setContentType(Consts.CONTENT_TYPE);

        Map<String, String> map = new HashMap<String, String>();
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        String url = blobstoreService.createUploadUrl("/changeSettings");
        map.put("url", url);

        JSON.encode(map, this.response.getOutputStream());
        return null;
    }
}
