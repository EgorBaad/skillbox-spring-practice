package com.example.m3practice.repository;

import com.example.m3practice.data.Contact;
import com.example.m3practice.repository.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Contact> findAll() {
        String sql = "SELECT * FROM contact";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    public Optional<Contact> findById(String id) {
        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper())
                )
        );

        return Optional.ofNullable(contact);
    }

    public Contact save(Contact contact) {
        Contact oldContact = findById(contact.getId()).orElse(null);
        if (oldContact != null) {
            String sql = "UPDATE contact SET firstname = ?, lastname = ?, email = ?, phone = ?";
            jdbcTemplate.update(sql,
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhone());
        } else {
            String sql = "INSERT INTO contact (id, firstname, lastname, email, phone) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    contact.getId(),
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhone());
        }

        return contact;
    }

    public void delete(String id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
