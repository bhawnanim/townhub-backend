package com.townhubutils.contact.model.mapper;

import com.townhubutils.contact.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(rs.getInt("contactId"));
        contact.setPhoneNumber(rs.getString("phoneNumber"));
        contact.setEmail(rs.getString("email"));
        contact.setWorkNumber(rs.getString("workNumber"));
        return contact;
    }
}
