package com.townhublisting.listing.controller;

import com.townhublisting.listing.exception.ListingException;
import com.townhublisting.listing.model.Listing;
import com.townhublisting.listing.model.ListingTime;
import com.townhublisting.listing.service.ListingService;
import com.townhublisting.plans.exception.PlansException;
import com.townhubresponse.response.Result;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/listing")
public class ListingController {

    @Autowired
    ListingService listingService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Listing successfully saved"),
            @ApiResponse(code = 404, message = "Service Not Found", response = ListingException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ListingException.class)
    })
    public ResponseEntity<Result<Integer>> savePlan(@RequestBody Listing listing) throws Exception {
        Result<Integer> result = listingService.saveListing(listing);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PostMapping(value = "/addListingTime", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Listing successfully saved"),
            @ApiResponse(code = 404, message = "Service Not Found", response = ListingException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ListingException.class)
    })
    public ResponseEntity<Result<Integer>> addListingTime(@RequestBody ListingTime listingTime) throws Exception {
        Result<Integer> result = listingService.saveListingTime(listingTime);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Listing"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ListingException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ListingException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = ListingException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ListingException.class)
    })
    public ResponseEntity<Result<List<Listing>>> getAllListing() throws Exception {
        Result<List<Listing>> result = listingService.listAllListing();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Listing"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ListingException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ListingException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = ListingException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ListingException.class)
    })
    public ResponseEntity<Result<List<Listing>>> getAllVerifiedListing() throws Exception {
        Result<List<Listing>> result = listingService.listAllVerifiedListing();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/business/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Listing"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ListingException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ListingException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = ListingException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ListingException.class)
    })

    public ResponseEntity<Result<List<Listing>>> getAllListingByBusinessId(@PathVariable("id") int id) throws Exception {
        Result<List<Listing>> result = listingService.listAllListingByBusiness(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Listing"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = ListingException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ListingException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = ListingException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ListingException.class)
    })
    public ResponseEntity<Result<List<ListingTime>>> getListingTime(@PathVariable("id") int id) throws Exception {
        Result<List<ListingTime>> result = listingService.getListingTime(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{field}/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Plans status successfully updated"),
            @ApiResponse(code = 404, message = "Service Not Found", response = ListingException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ListingException.class)
    })
    public ResponseEntity<Result<Integer>> changeListingStatus(@PathVariable("field") String field,@PathVariable("id") int id, @PathVariable("status") boolean listingStatus) throws Exception {
        Result<Integer> result = listingService.changeListingStatus(field,id,listingStatus);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
