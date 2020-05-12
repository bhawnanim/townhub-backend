package com.townhubutils.address.controller;

import com.townhubresponse.response.Result;
import com.townhubutils.address.exception.AddressException;
import com.townhubutils.address.model.Address;
import com.townhubutils.address.service.AddressService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Address is successfully saved"),
            @ApiResponse(code = 404, message = "Service Not Found", response = AddressException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = AddressException.class)
    })
    public ResponseEntity<Result<Integer>> saveAddress(@RequestBody Address address) throws Exception {
        Result<Integer> result = addressService.saveAddress(address);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Address successfully edited"),
            @ApiResponse(code = 404, message = "Service Not Found", response = AddressException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = AddressException.class)
    })
    public ResponseEntity<Result<Integer>> updateAddress(@PathVariable("id") int id, @RequestBody Address address) throws Exception {
        Result<Integer> result = addressService.updateAddress(id, address);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Record successfully fetched"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = AddressException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = AddressException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = AddressException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = AddressException.class)
    })
    public ResponseEntity<Result<Address>> getAddressById(@PathVariable("id") int id) throws Exception {
        Result<Address> result = addressService.getAddressById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
