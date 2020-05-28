package com.townhubutils.query.service;


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
public class QueryProperties {
    @NotNull
    public QueryProperties.DbQueries dbQueries;

    @Data
    @Component
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DbQueries {
        private String getQueries;
        private String insertQuery;
        private String getQueriesById;
        private String getQueriesByListingId;
        private String getQueriesByBusinessId;
    }
}
