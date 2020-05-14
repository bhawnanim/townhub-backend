package com.townhubutils.contact.service;

import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import com.townhubutils.contact.model.Contact;
import com.townhubutils.contact.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepo contactRepo;

    public Result<Integer> saveContact(Contact contact) throws Exception {
        int result = contactRepo.saveContact(contact);
        if (result > 0) {
            return new Result<>(201, result);
        } else {
            throw new ResultException(new Result<>(400, "Please record contact details again properly.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record contact details again!!!")))));
        }
    }

    public Result<Integer> updateContact(int id, Contact contact) throws Exception {
        int result = contactRepo.updateContact(id, contact);
        if (result > 0) {
            return new Result<>(201, "Record successfully edited.");
        } else {
            throw new ResultException(new Result<>(400, "Please record contact details again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record contact details again!!!")))));
        }
    }

    public Result<Contact> getContactById(int contactId) throws Exception {
        List<Contact> list = contactRepo.getContactById(contactId);
        if (list.size() > 0) {
            return new Result<>(200, list.get(0));
        }
        throw new ResultException(new Result<>(500, "Contact not found" + contactId));
    }
}
