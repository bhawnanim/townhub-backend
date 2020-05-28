package com.townhublisting.listing.repository;

import com.townhublisting.listing.model.Listing;
import com.townhublisting.listing.model.ListingTime;

import java.util.List;

public interface ListingRepo {

    Integer saveListing(Listing listing) throws Exception;

    Integer saveListingTime(ListingTime listingTime) throws Exception;

    List<Listing> listAllListing() throws Exception;

    List<Listing> listAllVerifiedListing() throws Exception;

    List<Listing> listAllListingByBusiness(int id) throws Exception;

    List<ListingTime> getListingTime(int id) throws Exception;

    int changeListingStatus(String field, int id, boolean listingStatus) throws Exception;
}
