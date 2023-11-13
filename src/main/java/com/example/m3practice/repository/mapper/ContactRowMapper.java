package com.example.m3practice.repository.mapper;

import com.example.m3practice.data.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setId(rs.getString(Contact.Fields.id));
        contact.setFirstName(rs.getString(Contact.Fields.firstName));
        contact.setLastName(rs.getString(Contact.Fields.lastName));
        contact.setPhone(rs.getString(Contact.Fields.phone));
        contact.setEmail(rs.getString(Contact.Fields.email));

        return contact;
    }
}
