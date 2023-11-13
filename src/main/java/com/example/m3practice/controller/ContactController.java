package com.example.m3practice.controller;

import com.example.m3practice.app.ContactService;
import com.example.m3practice.data.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contacts", contactService.getAll());

        return "index";
    }

    @GetMapping("/contact/create")
    public String showNewContact(Model model) {
        model.addAttribute("contact", new Contact());

        return "edit";
    }

    @PostMapping("/contact/edit")
    public String editContact(@ModelAttribute Contact contact) {
        if (contact.getId().isEmpty()) {
            contact.setId(UUID.randomUUID().toString());
        }

        contactService.save(contact);

        return "redirect:/";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditContact(Model model, @PathVariable String id) {
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);

        return "edit";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable String id) {
        contactService.delete(id);

        return "redirect:/";
    }
}
