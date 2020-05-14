package com.townhubutils.query.model.mapper;

import com.townhubutils.query.model.Query;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryRowMapper implements RowMapper<Query> {
    @Override
    public Query mapRow(ResultSet rs, int i) throws SQLException {
        Query query = new Query();
        query.setQueryId(rs.getInt("queryId"));
        query.setFirstName(rs.getString("firstName"));
        query.setLastName(rs.getString("lastName"));
        query.setPhoneNumber(rs.getString("phoneNumber"));
        query.setEmail(rs.getString("email"));
        query.setTitle(rs.getString("title"));
        query.setMessage(rs.getString("message"));
        query.setListingId(rs.getInt("listingId"));
        return query;
    }
}
