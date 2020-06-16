package com.townhublisting.listing.service;

import com.townhublisting.listing.model.Listing;
import com.townhublisting.listing.model.ListingTime;
import com.townhublisting.listing.repository.ListingRepo;
import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Service
public class ListingService {

    @Autowired
    ListingRepo listingRepo;

    public Result<Integer> saveListing(Listing listing) throws Exception {
        int result=listingRepo.saveListing(listing);
        if (result != 0) {
            return new Result<>(201, result);
        } else {
            throw new ResultException(new Result<>(400, "Please record Listing information again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record Listing information again!!!")))));
        }
    }

    public Result<Integer> saveListingTime(ListingTime listingTime) throws Exception {
        int result=listingRepo.saveListingTime(listingTime);
        if (result != 0) {
            return new Result<>(201, result);
        } else {
            throw new ResultException(new Result<>(400, "Please record Listing time information again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record Listing information again!!!")))));
        }
    }

    public Result<List<Listing>> listAllListing() throws Exception {
        List<Listing> list = listingRepo.listAllListing();
        Result result = null;
        if (!list.isEmpty()) {
            result = new Result<>(200, list);
        } else {
            throw new ResultException(new Result<>(404, "No Listing are there!!!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Listing not found")))));
        }
        return result;
    }

    public Result<List<Listing>> listAllVerifiedListing() throws Exception {
        List<Listing> list = listingRepo.listAllVerifiedListing();
        Result result = null;
        if (!list.isEmpty()) {
            result = new Result<>(200, list);
        } else {
            throw new ResultException(new Result<>(404, "No Listing are there!!!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Listing not found")))));
        }
        return result;
    }

    public Result<List<Listing>> listAllListingByBusiness(int id) throws Exception {
        List<Listing> list = listingRepo.listAllListingByBusiness(id);
        Result result = null;
        if (!list.isEmpty()) {
            result = new Result<>(200, list);
        } else {
            throw new ResultException(new Result<>(404, "No Listing are there!!!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Listing not found")))));
        }
        return result;
    }

    public Result<List<ListingTime>> getListingTime(int id) throws Exception {
        List<ListingTime> list = listingRepo.getListingTime(id);
        Result result = null;
        if (!list.isEmpty()) {
            result = new Result<>(200, list);
        } else {
            throw new ResultException(new Result<>(404, "No Listing are there!!!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Listing not found")))));
        }
        return result;
    }

    public Result<Integer> changeListingStatus(String field, int id, boolean listingStatus) throws Exception {
        int result = listingRepo.changeListingStatus(field,id, listingStatus);
        if (result != 0) {
            return new Result<>(201, "Plan Status updated.");
        } else {
            throw new ResultException(new Result<>(400, "Please record plan status again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record plan status again!!!")))));
        }
    }

}

