package com.anuj.addressbook;

import com.anuj.addressbook.model.AddressBook;
import com.anuj.addressbook.model.Contact;
import com.anuj.addressbook.service.AddressBookService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

private Contact createContact() {
    return new Contact(
            "Anuj",
            "Vishwakarma",
            "Amsterdam",
            "NorthHolland",
            "Geornite",
            "565645",
            "9999999999",
            "av@gmail.com"
    );
}

@Test
public void givenValidContact_whenAdded_shouldReturnSameContact() {

    AddressBookService service = new AddressBookService();

    Contact contact = createContact();

    Contact result = service.addContact("personal", contact);

    assertEquals("Anuj", result.getFirstName());
    assertEquals("Vishwakarma", result.getLastName());
}

@Test
public void givenContact_whenAdded_shouldCreateAddressBookAutomatically() {

    AddressBookService service = new AddressBookService();

    Contact contact = createContact();

    service.addContact("office", contact);

    assertNotNull(service.getAddressBook("office"));
}

@Disabled
@Test
public void givenMultipleContacts_whenAdded_shouldStoreAllContacts() {

    AddressBookService service = new AddressBookService();

    Contact c1 = createContact();
    Contact c2 = createContact();

    service.addContact("personal", c1);
    service.addContact("personal", c2);

    assertEquals(2,
            service.getAddressBook("personal").getContacts().size());
}

@Test
public void givenDifferentAddressBooks_whenAddingContacts_shouldSeparateData() {

    AddressBookService service = new AddressBookService();

    Contact c1 = createContact();
    Contact c2 = createContact();

    service.addContact("personal", c1);
    service.addContact("office", c2);

    assertEquals(1,
            service.getAddressBook("personal").getContacts().size());

    assertEquals(1,
            service.getAddressBook("office").getContacts().size());
}

@Test
public void givenContactWithNullValues_whenAdded_shouldNotCrash() {

    AddressBookService service = new AddressBookService();

    Contact contact = new Contact();

    Contact result = service.addContact("personal", contact);

    assertNotNull(result);
}

@Test
public void givenExistingContact_whenUpdated_shouldReturnUpdatedContact() {

    AddressBookService service = new AddressBookService();

    Contact original = createContact();

    service.addContact("personal", original);

    Contact updated = new Contact(
            "Anuj",
            "Vishwakarma",
            "Future City",
            "Nova",
            "Geornite",
            "565645",
            "8888888888",
            "anuj@update.com"
    );

    Contact result = service.updateContact(
            "personal",
            "Anuj",
            "Vishwakarma",
            updated
    );

    assertEquals("Future City", result.getCity());
    assertEquals("8888888888", result.getPhoneNumber());
}

@Test
public void givenExistingContact_whenDeleted_shouldReturnTrue() {

    AddressBookService service = new AddressBookService();

    Contact contact = createContact();

    service.addContact("personal", contact);

    boolean result = service.deleteContact(
            "personal",
            "Anuj",
            "Vishwakarma"
    );

    assertTrue(result);
}

@Test
public void givenNewBookName_whenCreated_shouldReturnAddressBook() {

    AddressBookService service = new AddressBookService();

    AddressBook book = service.createAddressBook("personal");

    assertEquals("personal", book.getName());
}

@Test
public void givenDuplicateContact_whenAdded_shouldThrowException() {

    AddressBookService service = new AddressBookService();

    Contact c1 = new Contact(
            "Anuj","Vishwakarma","Amsterdam","NorthHolland",
            "Geornite","565645","9999999999","av@gmail.com");

    Contact c2 = new Contact(
            "Anuj","Vishwakarma","Other","Other",
            "Other","111111","9999999999","dup@gmail.com");

    service.addContact("personal", c1);

    assertThrows(RuntimeException.class, () -> {
        service.addContact("personal", c2);
    });
}


}
