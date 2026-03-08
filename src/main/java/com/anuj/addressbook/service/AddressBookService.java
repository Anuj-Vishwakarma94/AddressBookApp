package com.anuj.addressbook.service;

import com.anuj.addressbook.model.AddressBook;
import com.anuj.addressbook.model.Contact;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class AddressBookService {

    private Map<String, AddressBook> addressBooks = new HashMap<>();

    public Contact addContact(String bookName, Contact contact) {

        AddressBook book = addressBooks.get(bookName);

        if (book == null) {
            book = new AddressBook(bookName);
            addressBooks.put(bookName, book);
        }

        book.addContact(contact);

        return contact;
    }

    public Contact updateContact(String bookName, String firstName, String lastName, Contact updated) {

        AddressBook book = addressBooks.get(bookName);

        if (book == null) {
            return null;
        }

        for (Contact contact : book.getContacts()) {

            if (contact.getFirstName().equals(firstName) &&
                contact.getLastName().equals(lastName)) {

                contact.setFirstName(updated.getFirstName());
                contact.setLastName(updated.getLastName());
                contact.setAddress(updated.getAddress());
                contact.setCity(updated.getCity());
                contact.setState(updated.getState());
                contact.setZip(updated.getZip());
                contact.setPhoneNumber(updated.getPhoneNumber());
                contact.setEmail(updated.getEmail());

                return contact;
            }
        }

        return null;
    }

    public AddressBook getAddressBook(String name) {
        return addressBooks.get(name);
    }
}