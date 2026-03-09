package com.anuj.addressbook.controller;

import com.anuj.addressbook.model.Contact;
import com.anuj.addressbook.service.AddressBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final AddressBookService service;

    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    @PostMapping("/{bookName}/contacts")
    public Contact addContact(
            @PathVariable String bookName,
            @RequestBody Contact contact) {

        return service.addContact(bookName, contact);
    }

    @PutMapping("/{bookName}/contacts")
    public Contact updateContact(
            @PathVariable String bookName,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestBody Contact contact) {

        return service.updateContact(bookName, firstName, lastName, contact);
    }

    @DeleteMapping("/{bookName}/contacts")
    public String deleteContact(
            @PathVariable String bookName,
            @RequestParam String firstName,
            @RequestParam String lastName) {

        boolean deleted = service.deleteContact(bookName, firstName, lastName);

        if (deleted) {
            return "Contact deleted successfully";
        }

        return "Contact not found";
    }

    @GetMapping("/{bookName}/contacts")
    public List<Contact> getContacts(@PathVariable String bookName) {

        return service.getContacts(bookName);
    }
}