package com.townhubutils.category.service;

import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import com.townhubutils.category.model.Category;
import com.townhubutils.category.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public Result<List<Category>> findAllCategories() throws Exception {
        List<Category> list = categoryRepo.findAllCategories();
        Result result = null;
        if (!list.isEmpty()) {
            result = new Result<>(200, list);
        } else {
            throw new ResultException(new Result<>(404, "No Categories are there!!!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Categories not found")))));
        }
        return result;
    }

    public Result<Integer> saveCategory(Category category) throws Exception {
        int result = categoryRepo.saveCategory(category);
        if (result != 0) {
            return new Result<>(201, "Record successfully saved.");
        } else {
            throw new ResultException(new Result<>(400, "Please record category again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record category again!!!")))));
        }
    }

    public Result<Integer> updateCategory(int id, Category category) throws Exception {
        int result = categoryRepo.updateCategory(id, category);
        if (result != 0) {
            return new Result<>(201, "Record successfully edited.");
        } else {
            throw new ResultException(new Result<>(400, "Please record category again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record category again!!!")))));
        }
    }
}
