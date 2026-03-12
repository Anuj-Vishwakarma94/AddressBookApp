package com.anuj.addressbook.storage;

import com.anuj.addressbook.model.Contact;
import com.anuj.addressbook.util.JSONUtil;

import java.util.List;

public class JSONStorage implements ContactStorage {

    @Override
    public void save(String filePath, List<Contact> contacts) {
        JSONUtil.writeContactsToJSON(filePath, contacts);
    }

    @Override
    public List<Contact> load(String filePath) {
        return JSONUtil.readContactsFromJSON(filePath);
    }
}