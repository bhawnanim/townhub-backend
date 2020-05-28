package com.townhublisting.listing.service;

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
public class ListingProperties {

    @NotNull
    public DbQueries dbQueries;

    @Data
    @Component
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DbQueries {
        private String insertListing;
        private String insertListingTime;
        private String getAllListing;
        private String getListingTime;
        private String getAllListingByBusiness;
        private String changeListingActive;
        private String changeListingVerify;
        private String getAllVerifiedListing;
    }
}
