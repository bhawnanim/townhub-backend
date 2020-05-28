package com.townhublisting.listing.model.mapper;


import com.townhublisting.listing.model.ListingTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListingTimeMapper implements RowMapper<ListingTime> {
    @Override
    public ListingTime mapRow(ResultSet rs, int i) throws SQLException {
        ListingTime listingTime=new ListingTime();
        listingTime.setTimingId(rs.getInt(1));
        listingTime.setListingId(rs.getInt(2));
        listingTime.setMondayOpen(rs.getString(3));
        listingTime.setMondayClose(rs.getString(4));
        listingTime.setTuesdayOpen(rs.getString(5));
        listingTime.setTuesdayClose(rs.getString(6));
        listingTime.setWednesdayOpen(rs.getString(7));
        listingTime.setWednesdayClose(rs.getString(8));
        listingTime.setThursdayOpen(rs.getString(9));
        listingTime.setThursdayClose(rs.getString(10));
        listingTime.setFridayOpen(rs.getString(11));
        listingTime.setFridayClose(rs.getString(12));
        listingTime.setSaturdayOpen(rs.getString(13));
        listingTime.setSaturdayClose(rs.getString(14));
        listingTime.setSundayOpen(rs.getString(15));
        listingTime.setSundayClose(rs.getString(16));
        return listingTime;
    }
}
