package com.aknow.masterpiece.service;

import java.util.ArrayList;
import java.util.List;

import com.aknow.masterpiece.meta.ItemMeta;
import com.aknow.masterpiece.model.Item;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;


public class CategorySearchService {

    @SuppressWarnings("static-method")
    public int getItemCount(String categoryCode){
        ItemMeta meta = new ItemMeta();
        int count = Datastore.query(meta).filter(meta.categoryCode.equal(categoryCode)).count();
        return count;
    }
    @SuppressWarnings("static-method")
    public List<Item> getItemList(String categoryCode, int pageCount){
        List<Item> itemList = new ArrayList<Item>();
        ItemMeta meta = new ItemMeta();
        List<Key> keyList = Datastore.query(meta).filter(meta.categoryCode.equal(categoryCode)).sort(meta.postDate.desc).asKeyList();
        int start = (pageCount - 1) * 30;
        int count = 0;
        for(int i = start; i < keyList.size(); i++){
            itemList.add(Datastore.get(Item.class, keyList.get(i)));
            count++;
            if(count == 30) break;
        }
        return itemList;
    }
}
