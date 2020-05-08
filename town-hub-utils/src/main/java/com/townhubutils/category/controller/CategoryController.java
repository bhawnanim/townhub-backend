package com.townhubutils.category.controller;

import com.townhubresponse.response.Result;
import com.townhubutils.category.exception.CategoryException;
import com.townhubutils.category.model.Category;
import com.townhubutils.category.service.CategoryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Categories"),
            @ApiResponse(code = 401, message = "UnAuthorized", response = CategoryException.class),
            @ApiResponse(code = 403, message = "Forbidden", response = CategoryException.class),
            @ApiResponse(code = 404, message = "Service Not Found", response = CategoryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = CategoryException.class)
    })
    public ResponseEntity<Result<List<Category>>> getAllCategories() throws Exception {
        Result<List<Category>> result = categoryService.findAllCategories();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category successfully saved"),
            @ApiResponse(code = 404, message = "Service Not Found", response = CategoryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = CategoryException.class)
    })
    public ResponseEntity<Result<Integer>> saveCategory(@RequestBody Category category) throws Exception {
        Result<Integer> result = categoryService.saveCategory(category);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category successfully edited"),
            @ApiResponse(code = 404, message = "Service Not Found", response = CategoryException.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = CategoryException.class)
    })
    public ResponseEntity<Result<Integer>> updateCategory(@PathVariable("id") int id, @RequestBody Category category) throws Exception {
        Result<Integer> result = categoryService.updateCategory(id, category);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
