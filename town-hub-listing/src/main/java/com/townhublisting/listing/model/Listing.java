package com.townhublisting.listing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Listing {
    private int listingId;
    private int profileId;
    private int categoryId;
    private int addressId;
    private int contactId;
    private String listingName;
    private String listingTagLine;
    private String listingDescription;
    private int minPrice;
    private int maxPrice;
    private String website;
    private String listingImage;
    private boolean listingActive;
    private boolean listingVerify;
    private String city;
    private String categoryName;
    private String categorySmallIcon;
    private String email;
}
