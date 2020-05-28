package com.townhubutils.query.controller;

import com.townhubresponse.response.Result;
import com.townhubutils.query.client.MailClient;
import com.townhubutils.query.exception.QueryException;
import com.townhubutils.query.model.EmailModal;
import com.townhubutils.query.model.Query;
import com.townhubutils.query.service.QueryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/queries")
public class QueryController {
    @Autowired
    QueryService queryService;

    @Autowired
    MailClient mailClient;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Query is successfully submitted"),
            @ApiResponse(code = 404, message = "Service Not Found", response = QueryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = QueryException.class)
    })
    public ResponseEntity<Result<Integer>> submitQuery(@RequestBody Query query) throws Exception {
        mailClient.sendMail(EmailModal.builder().ccEmail(query.getEmail()).emailTo("mukeshbhawnani5@gmail.com").subjact(query.getTitle()).text(query.getMessage()).build());
        Result<Integer> result = queryService.submitQuery(query);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Queries fetched"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = QueryException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = QueryException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = QueryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = QueryException.class)
    })
    public ResponseEntity<Result<List<Query>>> getAllQueries() throws Exception {
        Result<List<Query>> result = queryService.getAllQueries();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Record successfully fetched"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = QueryException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = QueryException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = QueryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = QueryException.class)
    })
    public ResponseEntity<Result<Query>> getQueryById(@PathVariable("id") int id) throws Exception {
        Result<Query> result = queryService.getQueryById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/listingQueries/{listingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Record successfully fetched"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = QueryException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = QueryException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = QueryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = QueryException.class)
    })
    public ResponseEntity<Result<Query>> getQueryByListingId(@PathVariable("listingId") int listingId) throws Exception {
        Result<Query> result = queryService.getQueryByListingId(listingId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/businessQueries/{businessId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Record successfully fetched"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = QueryException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = QueryException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = QueryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = QueryException.class)
    })
    public ResponseEntity<Result<List<Query>>> getQueryByBusinessId(@PathVariable("businessId") int businessId) throws Exception {
        Result<List<Query>> result = queryService.getQueryByBusinessId(businessId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
