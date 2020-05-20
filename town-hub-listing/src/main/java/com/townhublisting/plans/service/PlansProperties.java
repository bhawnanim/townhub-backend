package com.townhublisting.plans.service;

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
public class PlansProperties {
    @NotNull
    public DbQueries dbQueries;

    @Data
    @Component
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DbQueries {
        private String getPlans;
        private String insertPlan;
        private String updatePlan;
        private String changePlanStatus;
    }
}
