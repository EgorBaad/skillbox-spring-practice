package org.example;

import org.example.dto.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("default")
public class DefaultContactsLoader implements ContactsLoader{
    @Override
    public List<Contact> loadContacts() {
        System.out.println("Default profile. Returning empty list");
        return new ArrayList<>();
    }
}
