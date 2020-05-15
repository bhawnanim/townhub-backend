package com.townhubutils.query.service;

import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import com.townhubutils.query.client.MailClient;
import com.townhubutils.query.model.EmailModal;
import com.townhubutils.query.model.Query;
import com.townhubutils.query.repository.QueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QueryService {
    @Autowired
    QueryRepo queryRepo;



    public Result<Integer> submitQuery(Query query) throws Exception {
        int result = queryRepo.submitQuery(query);
        if (result > 0) {
            return new Result<>(201, "Record successfully submitted.");
        } else {
            throw new ResultException(new Result<>(400, "Please record query again properly.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record query again!!!")))));
        }
    }

    public Result<List<Query>> getAllQueries() throws Exception {
        List<Query> list = queryRepo.getAllQueries();
        Result result = null;
        if (!list.isEmpty()) {
            result = new Result<>(200, list);
        } else {
            throw new ResultException(new Result<>(404, "No Queries are there!!!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Queries not found")))));
        }
        return result;
    }

    public Result<Query> getQueryById(int queryId) throws Exception {
        List<Query> list = queryRepo.getQueryById(queryId);
        if (list.size() > 0) {
            return new Result<>(200, list.get(0));
        }
        throw new ResultException(new Result<>(500, "Query not found" + queryId));
    }

    public Result<Query> getQueryByListingId(int listingId) throws Exception {
        List<Query> list1 = queryRepo.getQueryByListingId(listingId);
        if (list1.size() > 0) {
            return new Result<>(200, list1.get(0));
        }
        throw new ResultException(new Result<>(500, "Query not found for your Listing" + listingId));
    }
}
