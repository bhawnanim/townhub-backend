package com.townhubutils.query.client;

import com.townhubutils.query.model.EmailModal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mailClient",url = "http://localhost:8084/api/v1/mails")
public interface MailClient {
    @PostMapping(value = "/")
    Boolean sendMail(@RequestBody EmailModal emailModal) throws Exception;
}
