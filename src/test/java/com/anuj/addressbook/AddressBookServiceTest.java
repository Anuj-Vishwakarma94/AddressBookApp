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

    Contact result = service.addContact("personal", createContact());

    assertEquals("Anuj", result.getFirstName());
    assertEquals("Vishwakarma", result.getLastName());
}

@Test
public void givenContact_whenAdded_shouldCreateAddressBookAutomatically() {

    AddressBookService service = new AddressBookService();

    service.addContact("office", createContact());

    assertNotNull(service.getAddressBook("office"));
}

@Test
public void givenDifferentAddressBooks_whenAddingContacts_shouldSeparateData() {

    AddressBookService service = new AddressBookService();

    service.addContact("personal", createContact());
    service.addContact("office", createContact());

    assertEquals(1, service.getAddressBook("personal").getContacts().size());
    assertEquals(1, service.getAddressBook("office").getContacts().size());
}

@Test
public void givenContactWithNullValues_whenAdded_shouldNotCrash() {

    AddressBookService service = new AddressBookService();

    Contact result = service.addContact("personal", new Contact());

    assertNotNull(result);
}

@Test
public void givenLongPhoneNumber_whenAdded_shouldStoreContact() {

    AddressBookService service = new AddressBookService();

    Contact contact = new Contact(
            "Anuj",
            "Vishwakarma",
            "Amsterdam",
            "NorthHolland",
            "Geornite",
            "565645",
            "999999999999999",
            "av@gmail.com"
    );

    service.addContact("personal", contact);

    assertEquals(1, service.getAddressBook("personal").getContacts().size());
}
@Test
public void givenExistingContact_whenUpdated_shouldReturnUpdatedContact() {

    AddressBookService service = new AddressBookService();

    Contact original = createContact();
    service.addContact("personal", original);

    Contact updated = new Contact(
            "Anuj",
            "Vishwakarma",
            "Nova",
            "Future City",
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

    assertEquals("Nova", result.getCity());
    assertEquals("8888888888", result.getPhoneNumber());
}

@Test
public void givenExistingContact_whenDeleted_shouldReturnTrue() {

    AddressBookService service = new AddressBookService();

    service.addContact("personal", createContact());

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
public void givenDuplicateBookName_whenCreated_shouldReturnExistingBook() {

    AddressBookService service = new AddressBookService();

    AddressBook b1 = service.createAddressBook("personal");
    AddressBook b2 = service.createAddressBook("personal");

    assertEquals(b1, b2);
}

@Test
public void givenContacts_whenSearchByCity_shouldReturnMatches() {

    AddressBookService service = new AddressBookService();

    Contact c1 = new Contact("Anuj","Vishwakarma","","NorthHolland","Geornite","","","");
    Contact c2 = new Contact("Rahul","Verma","Delhi","","DL","","","");

    service.addContact("personal", c1);
    service.addContact("office", c2);
    assertEquals(1, service.searchByCity("Delhi").size());
   
}

@Test
public void givenContacts_whenSearchByState_shouldReturnMatches() {

    AddressBookService service = new AddressBookService();

    Contact c1 = new Contact("Anuj","Vishwakarma","","NorthHolland","Geornite","","","");
    Contact c2 = new Contact("Rahul","Verma","Delhi","DL","","","","");

    service.addContact("personal", c1);
    service.addContact("office", c2);

    assertEquals(1, service.searchByState("DL").size());
}

}
