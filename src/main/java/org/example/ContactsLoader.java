package org.example;

import org.example.dto.Contact;

import java.util.List;

public interface ContactsLoader {
    List<Contact> loadContacts();
}
