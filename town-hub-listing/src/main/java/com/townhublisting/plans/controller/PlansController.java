package com.townhublisting.plans.controller;

import com.townhublisting.plans.exception.PlansException;
import com.townhublisting.plans.model.Plans;
import com.townhublisting.plans.service.PlansService;
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
@RequestMapping("api/v1/plans")
public class PlansController {
    @Autowired
    PlansService plansService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Plans"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = PlansException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = PlansException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = PlansException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = PlansException.class)
    })
    public ResponseEntity<Result<List<Plans>>> getAllPlans() throws Exception {
        Result<List<Plans>> result = plansService.listAllPlans();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Plans successfully saved"),
            @ApiResponse(code = 404, message = "Service Not Found", response = PlansException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = PlansException.class)
    })
    public ResponseEntity<Result<Integer>> savePlan(@RequestBody Plans plans) throws Exception {
        Result<Integer> result = plansService.savePlan(plans);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Plans successfully edited"),
            @ApiResponse(code = 404, message = "Service Not Found", response = PlansException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = PlansException.class)
    })
    public ResponseEntity<Result<Integer>> updatePlan(@PathVariable("id") int id, @RequestBody Plans plans) throws Exception {
        Result<Integer> result = plansService.updatePlan(id, plans);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Plans status successfully updated"),
            @ApiResponse(code = 404, message = "Service Not Found", response = PlansException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = PlansException.class)
    })
    public ResponseEntity<Result<Integer>> changePlanStatus(@PathVariable("id") int id, @PathVariable("status") boolean plansStatus) throws Exception {
        Result<Integer> result = plansService.changePlanStatus(id, plansStatus);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
