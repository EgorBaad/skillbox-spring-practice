package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ContactProcessor contactProcessor = context.getBean(ContactProcessor.class);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("""
                Add new contact by typing it in format "NAME;PHONE;EMAIL"
                Type "show all" to see all contacts
                Type "remove" to remove contact
                Type "exit" to exit the app""");
        String command;
        while (true) {
            try {
                command = reader.readLine();
                if (command == null) {
                    command = "";
                } else if (command.matches(".+;.+;.+")) {
                    if(contactProcessor.addContact(command)) {
                        System.out.println("Contact added");
                    } else {
                        System.out.println("Could not add contact");
                    }
                } else if (command.equals("show all")) {
                    System.out.println(contactProcessor.showContacts());
                } else if (command.equals("remove")) {
                    System.out.println("Please input the Email of the contact you need to remove");
                    command = reader.readLine();
                    if (contactProcessor.removeContactByEmail(command)) {
                        System.out.println("Successfully removed contact " + command);
                    } else {
                        System.out.println("Could not find specified contact");
                    }
                } else if (command.equals("dump")) {
                    if (contactProcessor.dumpToFile()) {
                        System.out.println("Dumped successfully");
                    } else {
                        System.out.println("Could not dump to file");
                    }
                } else if (command.equals("exit")) {
                    break;
                } else {
                    System.out.println("Unknown command");
                }
            } catch (IOException e) {
                command = "";
                System.out.println("Caught IOException. Be careful next time. Details: " + e.getMessage());
            }
        }
    }
}
