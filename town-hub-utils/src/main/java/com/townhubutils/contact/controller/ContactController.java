package com.townhubutils.contact.controller;

import com.townhubresponse.response.Result;
import com.townhubutils.contact.exception.ContactException;
import com.townhubutils.contact.model.Contact;
import com.townhubutils.contact.service.ContactService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Contact is successfully saved"),
            @ApiResponse(code = 404, message = "Service Not Found", response = ContactException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ContactException.class)
    })
    public ResponseEntity<Result<Integer>> saveContact(@RequestBody Contact contact) throws Exception {
        Result<Integer> result = contactService.saveContact(contact);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contact successfully edited"),
            @ApiResponse(code = 404, message = "Service Not Found", response = ContactException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ContactException.class)
    })
    public ResponseEntity<Result<Integer>> updateContact(@PathVariable("id") int id, @RequestBody Contact contact) throws Exception {
        Result<Integer> result = contactService.updateContact(id, contact);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Record successfully fetched"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ContactException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ContactException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = ContactException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ContactException.class)
    })
    public ResponseEntity<Result<Contact>> getAddressById(@PathVariable("id") int id) throws Exception {
        Result<Contact> result = contactService.getContactById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
