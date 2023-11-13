package com.example.m3practice.app;

import com.example.m3practice.data.Contact;
import com.example.m3practice.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public Contact findById(String id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        contactRepository.delete(id);
    }
}
