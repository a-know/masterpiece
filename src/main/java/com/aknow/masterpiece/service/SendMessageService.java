package com.aknow.masterpiece.service;

import java.util.Calendar;
import java.util.TimeZone;

import com.aknow.masterpiece.meta.UserMeta;
import com.aknow.masterpiece.model.Message;
import com.aknow.masterpiece.model.User;

import org.slim3.datastore.Datastore;


public class SendMessageService {

    @SuppressWarnings("static-method")
    public Message sendMessage(String to, String sender, String content){

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));

        UserMeta userMeta = new UserMeta();
        User user = Datastore.query(userMeta).filter(userMeta.loginID.equal(to)).asSingle();

        Message m = new Message();

        String sender_replace = null;
        String content_replace = null;

        sender_replace = sender.replaceAll("<", "&lt;");
        sender_replace = sender_replace.replaceAll(">", "&gt;");

        content_replace = content.replaceAll("<", "&lt;");
        content_replace = content_replace.replaceAll(">", "&gt;");

        m.setTo(to);
        m.setSender(sender_replace);
        m.setContent(content_replace);
        m.setSendDate(calendar.getTime());
        m.setIsUnread(Boolean.TRUE);
        m.getUserRef().setModel(user);

        Datastore.put(m);

        return m;
    }

}
