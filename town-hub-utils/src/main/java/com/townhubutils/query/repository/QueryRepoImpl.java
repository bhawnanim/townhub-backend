package com.townhubutils.query.repository;

import com.townhubutils.query.model.Query;
import com.townhubutils.query.model.mapper.QueryRowMapper;
import com.townhubutils.query.service.QueryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "QueryRepo")
public class QueryRepoImpl implements QueryRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    QueryProperties serviceProperties;

    @Override
    public List<Query> getAllQueries() throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetQueries(), new QueryRowMapper());
    }

    @Override
    public Integer submitQuery(Query query) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getInsertQuery(), query.getListingId(), query.getFirstName(), query.getLastName(), query.getTitle(), query.getMessage(), query.getPhoneNumber(), query.getEmail());
    }

    @Override
    public List<Query> getQueryById(int queryId) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetQueriesById(), new QueryRowMapper(), queryId);
    }

    @Override
    public List<Query> getQueryByListingId(int listingId) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetQueriesByListingId(), new QueryRowMapper(), listingId);
    }
}
