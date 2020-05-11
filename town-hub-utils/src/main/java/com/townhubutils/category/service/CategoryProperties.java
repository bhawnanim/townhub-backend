package com.townhubutils.category.service;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
@Builder
@ConfigurationProperties(prefix = "db")
public class CategoryProperties {

    @NotNull
    public DbQueries dbQueries;

    @Data
    @Component
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DbQueries {
        private String getCategories;
        private String insertCategory;
        private String updateCategory;
        private String changeCategoryStatus;
    }
}