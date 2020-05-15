package com.townhubprofile.profile.clients;

import com.townhubprofile.profile.model.Contact;
import com.townhubresponse.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "contactClient",url = "${clients.contactUrl}")
public interface ContactClient {
    @PostMapping(value = "/")
    ResponseEntity<Result<Integer>> saveContact(@RequestBody Contact contact) throws Exception;
}
