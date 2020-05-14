package com.townhubprofile.profile.clients;

import com.townhubprofile.profile.model.Address;
import com.townhubresponse.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "addressClient",url = "http://localhost:8082/api/v1/addresses")
public interface AddressClient {
    @PostMapping(value = "/")
    ResponseEntity<Result<Integer>> saveAddress(@RequestBody Address address) throws Exception;
}
