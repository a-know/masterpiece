package com.aknow.masterpiece.service;

import com.aknow.masterpiece.meta.ItemMeta;
import com.aknow.masterpiece.model.Item;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.aknow.masterpiece.util.Consts;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;


public class EditItemService {
    public Item editItem(String loginID, String editTargetLoginID, String category, String name, String comment, String url, String key){

        if(!loginID.equals(editTargetLoginID)){//TODO nullpoのときがある
            return null;
        }

        Transaction tx = Datastore.beginTransaction();

        //itemの更新
        Item item = Datastore.get(Item.class, KeyFactory.stringToKey(key));
        item.setName(replaceCharacters(name));
        item.setCategoryCode(category);
        item.setComment(replaceCharacters(comment));
        item.setUrl(url);
        Datastore.put(item);

        //commit
        tx.commit();
        Memcache.delete(Consts.SlideDataList_KEY);
        Memcache.delete(Consts.ItemListForUserTop_KEY + loginID);

        return item;
    }

    @SuppressWarnings("static-method")
    public String replaceCharacters(String target){

        //不要なタグの置き換え
        String replace = null;

        replace = target.replaceAll("<", "&lt;");
        replace = replace.replaceAll(">", "&gt;");
        replace = replace.replaceAll("\n", "<br>");

        return replace;
    }

    @SuppressWarnings("static-method")
    public Integer getItemNo(String loginID){
        ItemMeta meta = new ItemMeta();
        int count = Datastore.query(meta).filter(meta.loginID.equal(loginID)).count();
        count++;
        return Integer.valueOf(count);
    }
}
