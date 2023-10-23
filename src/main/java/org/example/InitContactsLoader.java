package org.example;

import org.example.dto.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("init")
public class InitContactsLoader implements ContactsLoader {
    @Value("${app.contacts.initfile}")
    private String filepath;

    @Override
    public List<Contact> loadContacts() {
        System.out.println("Init profile. Loading contacts from file");
        List<Contact> contacts = new ArrayList<>();
        InputStream inputStream = getClass().getResourceAsStream(filepath);
        try {
            if (inputStream == null) {
                throw new NullPointerException("Could not find the file");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                String[] contactFields = line.split(";");
                if (contactFields.length == 3) {
                    contacts.add(new Contact(
                            contactFields[0].trim(),
                            contactFields[1].trim(),
                            contactFields[2].trim()
                    ));
                } else {
                    System.out.println("Invalid line in file: " + line + "\n" +
                            "Make sure it complies with pattern \"NAME;PHONE;EMAIL\"");
                }
            }
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Caught an exception while reading from file: " + e.getMessage());
        }

        return contacts;
    }
}
