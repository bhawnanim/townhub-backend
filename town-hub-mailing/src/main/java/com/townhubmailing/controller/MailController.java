package com.townhubmailing.controller;

import com.townhubmailing.modal.EmailModal;
import com.townhubmailing.service.MailService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mails")
public class MailController {
    @Autowired
    private MailService mailService;
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mail Sent Succesfully"),
            @ApiResponse(code = 404, message = "Service Not Found", response = Exception.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Exception.class)
    })
    public Boolean sendMail(@RequestBody EmailModal emailModal) throws Exception
    {
        return mailService.sendEmail(emailModal);
    }

}

