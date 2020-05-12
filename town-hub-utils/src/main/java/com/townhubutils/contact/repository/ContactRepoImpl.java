package com.townhubutils.contact.repository;

import com.townhubutils.contact.model.Contact;
import com.townhubutils.contact.model.mapper.ContactRowMapper;
import com.townhubutils.contact.service.ContactProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "ContactRepo")
public class ContactRepoImpl implements ContactRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ContactProperties serviceProperties;

    @Override
    public Integer saveContact(Contact contact) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getInsertContact(), contact.getPhoneNumber(), contact.getEmail(), contact.getWorkNumber());
    }

    @Override
    public Integer updateContact(int contactId, Contact contact) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getUpdateContact(), contact.getPhoneNumber(), contact.getEmail(), contact.getWorkNumber(), contactId);
    }

    @Override
    public List<Contact> getContactById(int contactId) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetContactById(), new ContactRowMapper(), contactId);
    }
}
