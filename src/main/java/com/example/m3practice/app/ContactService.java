package com.example.m3practice.app;

import com.example.m3practice.data.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();

    void save(Contact contact);

    Contact findById(String id);

    void delete(String id);
}
