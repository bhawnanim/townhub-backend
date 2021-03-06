package com.townhubutils.category.repository;

import com.townhubutils.category.model.Category;
import com.townhubutils.category.model.mapper.CategoryRowMapper;
import com.townhubutils.category.service.CategoryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "CategoryRepo")
public class CategoryRepoImpl implements CategoryRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CategoryProperties serviceProperties;

    @Override
    public List<Category> findAllCategories() throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetCategories(), new CategoryRowMapper());
    }

    @Override
    public Integer saveCategory(Category category) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getInsertCategory(), category.getCategoryName(), category.getCategoryDescription(), category.isCategoryActive(), category.getCategorySmallIconURL(), category.getCategoryBigIconURL());
    }

    @Override
    public Integer updateCategory(int categoryId, Category category) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getUpdateCategory(), category.getCategoryName(), category.getCategoryDescription(), category.isCategoryActive(), category.getCategorySmallIconURL(), category.getCategoryBigIconURL(), categoryId);
    }

    @Override
    public Integer changeCategoryStatus(int categoryId, boolean categoryStatus) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getChangeCategoryStatus(), categoryStatus, categoryId);
    }

    @Override
    public Integer getCategoryCount(int id) throws Exception {
        return jdbcTemplate.queryForObject(serviceProperties.getDbQueries().getGetCategoryCount(),new Object[]{id},Integer.class);
    }

}