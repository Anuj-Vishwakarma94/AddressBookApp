package com.anuj.addressbook.threads;

import com.anuj.addressbook.model.Contact;
import com.anuj.addressbook.repository.ContactRepository;

public class AddContactTask implements Runnable {

    private final ContactRepository repository;
    private final Contact contact;

    public AddContactTask(ContactRepository repository, Contact contact) {
        this.repository = repository;
        this.contact = contact;
    }

    @Override
    public void run() {
        repository.addContact(contact);
        System.out.println("Inserted contact: " + contact.getFirstName());
    }
}