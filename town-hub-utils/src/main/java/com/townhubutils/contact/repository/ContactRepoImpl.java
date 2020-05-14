package com.townhubutils.contact.repository;

import com.townhubutils.contact.model.Contact;
import com.townhubutils.contact.model.mapper.ContactRowMapper;
import com.townhubutils.contact.service.ContactProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository(value = "ContactRepo")
public class ContactRepoImpl implements ContactRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ContactProperties serviceProperties;

    @Override
    public Integer saveContact(Contact contact) throws Exception {
        KeyHolder holder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps=connection.prepareStatement(serviceProperties.getDbQueries().getInsertContact(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,contact.getPhoneNumber());
                ps.setString(2,contact.getEmail());
                ps.setString(3,contact.getWorkNumber());
                return ps;
            }
        }, holder);
        int newContactId=holder.getKey().intValue();
        return newContactId;
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
