package com.anuj.addressbook.storage;

import com.anuj.addressbook.model.Contact;
import com.anuj.addressbook.util.CSVUtil;

import java.util.List;

public class CSVStorage implements ContactStorage {

    @Override
    public void save(String filePath, List<Contact> contacts) {
        CSVUtil.writeContactsToCSV(filePath, contacts);
    }

    @Override
    public List<Contact> load(String filePath) {
        return CSVUtil.readContactsFromCSV(filePath);
    }
}