package com.townhubutils.query.repository;

import com.townhubutils.query.model.Query;

import java.util.List;

public interface QueryRepo {
    List<Query> getAllQueries() throws Exception;

    Integer submitQuery(Query query) throws Exception;

    List<Query> getQueryById(int queryId) throws Exception;

    List<Query> getQueryByListingId(int listingId) throws Exception;
}
