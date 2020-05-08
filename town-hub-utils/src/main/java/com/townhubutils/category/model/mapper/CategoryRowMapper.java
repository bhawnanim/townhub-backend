package com.townhubutils.category.model.mapper;

import com.townhubutils.category.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCategoryId(rs.getInt("categoryId"));
        category.setCategoryName(rs.getString("categoryName"));
        category.setCategoryDescription(rs.getString("categoryDescription"));
        category.setCategoryActive(rs.getBoolean("categoryActive"));
        category.setCategorySmallIconURL(rs.getString("categorySmallIconURL"));
        category.setCategoryBigIconURL(rs.getString("categoryBigIconURL"));
        return category;
    }

}
