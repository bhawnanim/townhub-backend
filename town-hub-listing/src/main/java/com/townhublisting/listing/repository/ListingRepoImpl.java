package com.townhublisting.listing.repository;

import com.townhublisting.listing.model.Listing;
import com.townhublisting.listing.model.ListingTime;
import com.townhublisting.listing.model.mapper.ListingMapper;
import com.townhublisting.listing.model.mapper.ListingTimeMapper;
import com.townhublisting.listing.service.ListingProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository(value = "ListingRepo")
public class ListingRepoImpl implements ListingRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ListingProperties serviceProperties;

    @Override
    public Integer saveListing(Listing listing) throws Exception {
        KeyHolder holder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps=connection.prepareStatement(serviceProperties.getDbQueries().getInsertListing(), Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,listing.getProfileId());
                ps.setInt(2,listing.getCategoryId());
                ps.setInt(3,listing.getAddressId());
                ps.setInt(4,listing.getContactId());
                ps.setString(5,listing.getListingName());
                ps.setString(6,listing.getListingDescription());
                ps.setInt(7,listing.getMinPrice());
                ps.setInt(8,listing.getMaxPrice());
                ps.setString(9,listing.getWebsite());
                ps.setString(10,listing.getListingImage());
                ps.setBoolean(11,listing.isListingActive());
                ps.setBoolean(12,listing.isListingVerify());
                ps.setString(13,listing.getListingTagLine());
                return ps;
            }
        }, holder);
        int newListingId=holder.getKey().intValue();
        return newListingId;
    }

    @Override
    public Integer saveListingTime(ListingTime listingTime) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getInsertListingTime(),listingTime.getListingId(),listingTime.getMondayOpen(),listingTime.getMondayClose(),listingTime.getTuesdayOpen(),listingTime.getTuesdayClose(),listingTime.getWednesdayOpen(),listingTime.getWednesdayClose(),listingTime.getThursdayOpen(),listingTime.getThursdayClose(),listingTime.getFridayOpen(),listingTime.getFridayClose(),listingTime.getSaturdayOpen(),listingTime.getSaturdayClose(),listingTime.getSundayOpen(),listingTime.getSundayClose());
    }

    @Override
    public List<Listing> listAllListing() throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetAllListing(), new ListingMapper());
    }

    @Override
    public List<Listing> listAllVerifiedListing() throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetAllVerifiedListing(), new ListingMapper());
    }

    @Override
    public List<Listing> listAllListingByBusiness(int id) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetAllListingByBusiness(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,id);
            }
        },new ListingMapper());
    }

    @Override
    public List<ListingTime> getListingTime(int id) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetListingTime(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,id);
            }
        },new ListingTimeMapper());
    }

    @Override
    public int changeListingStatus(String field, int id, boolean listingStatus) throws Exception {
        if(field.contentEquals("listingActive")){
            return jdbcTemplate.update(serviceProperties.getDbQueries().getChangeListingActive(),listingStatus,id);
        }else if(field.contentEquals("listingVerify")){
            return jdbcTemplate.update(serviceProperties.getDbQueries().getChangeListingVerify(),listingStatus,id);
        }
        else{
            return 0;
        }
    }
}
