package com.townhubutils.contact.repository;

import com.townhubutils.contact.model.Contact;

import java.util.List;

public interface ContactRepo {
    Integer saveContact(Contact contact) throws Exception;

    Integer updateContact(int contactId, Contact contact) throws Exception;

    List<Contact> getContactById(int contactId) throws Exception;
}
