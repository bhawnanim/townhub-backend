package com.townhubutils.category.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private boolean categoryActive;
    private String categorySmallIconURL;
    private String categoryBigIconURL;
}
