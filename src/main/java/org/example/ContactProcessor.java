package org.example;

import org.example.dto.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactProcessor {
    private final ContactsLoader contactsLoader;
    private List<Contact> contacts = new ArrayList<>();
    @Value("${app.contacts.saveto}")
    private String saveToFileName;

    @PostConstruct
    public void initContacts() {
        System.out.println("loading contacts");
        contacts = contactsLoader.loadContacts();
        System.out.println("Contacts loaded. Good to go!");
    }

    public ContactProcessor(ContactsLoader contactsLoader) {
        this.contactsLoader = contactsLoader;
    }

    public boolean addContact(String newContact) {
        String[] newContactFields = newContact.split(";");
        if (newContactFields.length == 3) {
            contacts.add(new Contact(
                    newContactFields[0].trim(),
                    newContactFields[1].trim(),
                    newContactFields[2].trim()
            ));
            return true;
        } else {
            System.out.println("Invalid format. Use format \"NAME;PHONE;EMAIL\"");
            return false;
        }
    }

    public boolean removeContactByEmail(String email) {
        return contacts.removeIf(c -> (c.getEmail().equals(email)));
    }

    public String showContacts() {
        return contacts.toString();
    }

    public boolean dumpToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveToFileName));
            for (Contact c:contacts) {
                writer.write(c.toString() + "\n");
            }
            writer.close();

            return true;
        } catch (Exception e) {
            System.out.println("Could not dump file. Exception: " + e.toString());
            return false;
        }
    }
}
