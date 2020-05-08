package com.townhubutils.category.repository;

import com.townhubutils.category.model.Category;

import java.util.List;

public interface CategoryRepo {
    List<Category> findAllCategories() throws Exception;

    Integer saveCategory(Category category) throws Exception;

    Integer updateCategory(int categoryId, Category category) throws Exception;
}
