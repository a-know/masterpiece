package com.aknow.masterpiece.service;

import java.util.HashMap;
import java.util.Map;

import com.aknow.masterpiece.model.Message;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;

import com.google.appengine.api.datastore.Key;


public class LoadMessageService {
    @SuppressWarnings("static-method")
    public Map<String, Object> loadMessage(Key key){

        Map<String, Object> messageMap = new HashMap<String, Object>();

        Message m = Datastore.get(Message.class, key);

        if(m.getIsUnread().booleanValue()){
            m.setIsUnread(Boolean.FALSE);
            Datastore.put(m);
        }

        //delete memcache
        Memcache.delete(Consts.UnreadMessageNumber_KEY + m.getTo());

        messageMap.put("sender", m.getSender());
        messageMap.put("content", m.getContent());

        return messageMap;
    }
}
