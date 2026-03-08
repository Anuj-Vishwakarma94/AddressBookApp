package com.anuj.addressbook;

import com.anuj.addressbook.model.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void givenValidContactDetails_whenObjectCreated_shouldReturnCorrectValues() {

        Contact contact = new Contact(
                "Anuj",
                "Vishwakarma",
                "Amsterdam",
                "NorthHolland",
                "Geornite",
                "565645",
                "9999999999",
                "av@gmail.com"
        );

        assertEquals("Anuj", contact.getFirstName());
        assertEquals("Vishwakarma", contact.getLastName());
        assertEquals("Amsterdam", contact.getCity());
        assertEquals("NorthHolland", contact.getState());
        assertEquals("9999999999", contact.getPhoneNumber());
    }


    @Test
    public void givenEmptyConstructor_whenSettersUsed_shouldReturnCorrectValues() {

        Contact contact = new Contact();

        contact.setFirstName("John");
        contact.setLastName("Cena");
        contact.setCity("Newjersey");

        assertEquals("John", contact.getFirstName());
        assertEquals("Cena", contact.getLastName());
        assertEquals("Newjersey", contact.getCity());
    }


    @Test
    public void givenTwoContactsWithSameName_whenCompared_shouldReturnEqual() {

        Contact c1 = new Contact(
                "Rohit", "Sharma",
                "Addr1", "City1", "State1",
                "111111", "9999999999", "a@mail.com"
        );

        Contact c2 = new Contact(
                "Rohit", "Sharma",
                "Addr2", "City2", "State2",
                "222222", "8888888888", "b@mail.com"
        );

        assertEquals(c1, c2);
    }


    @Test
    public void givenTwoContactsWithDifferentNames_whenCompared_shouldReturnNotEqual() {

        Contact c1 = new Contact("Rohit", "Sharma", "", "", "", "", "", "");
        Contact c2 = new Contact("Tilak", "Verma", "", "", "", "", "", "");

        assertNotEquals(c1, c2);
    }


    @Test
    public void givenSameContactObjects_whenHashCodeCalled_shouldReturnSameHash() {

        Contact c1 = new Contact("Rohit", "Sharma", "", "", "", "", "", "");
        Contact c2 = new Contact("Rohit", "Sharma", "", "", "", "", "", "");

        assertEquals(c1.hashCode(), c2.hashCode());
    }


    @Test
    public void givenContactWithNullValues_shouldHandleGracefully() {

        Contact contact = new Contact();

        assertNull(contact.getFirstName());
        assertNull(contact.getLastName());
        assertNull(contact.getCity());
    }


    @Test
    public void givenContact_whenUpdatingPhoneNumber_shouldReturnUpdatedValue() {

        Contact contact = new Contact();

        contact.setPhoneNumber("1111111111");
        contact.setPhoneNumber("9999999999");

        assertEquals("9999999999", contact.getPhoneNumber());
    }


    @Test
    public void givenContact_whenUpdatingEmail_shouldReturnUpdatedEmail() {

        Contact contact = new Contact();

        contact.setEmail("old@mail.com");
        contact.setEmail("new@mail.com");

        assertEquals("new@mail.com", contact.getEmail());
    }

}