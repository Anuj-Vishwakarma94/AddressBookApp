package com.anuj.addressbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.anuj.addressbook.repository.ContactRepository;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    ContactRepository repository;

    @Test
    public void givenDatabase_whenContactsFetched_shouldReturnRecords() {

    	assertNotNull(repository.getAllContacts());
    }
    
    @Test
    public void givenContact_whenCityUpdated_shouldReturnUpdatedRows() {

        int rows = repository.updateContactCity(
                "Anuj",
                "Vishwakarma",
                "Delhi"
        );

        assertTrue(rows > 0);
    }
}