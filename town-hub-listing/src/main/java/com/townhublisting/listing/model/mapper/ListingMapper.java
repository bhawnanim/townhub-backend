package com.townhublisting.listing.model.mapper;

import com.townhublisting.listing.model.Listing;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListingMapper implements RowMapper<Listing> {
    @Override
    public Listing mapRow(ResultSet rs, int i) throws SQLException {
        Listing listing= new Listing();
        listing.setListingId(rs.getInt("listingId"));
        listing.setProfileId(rs.getInt("profileId"));
        listing.setAddressId(rs.getInt("addressId"));
        listing.setContactId(rs.getInt("contactId"));
        listing.setCategoryId(rs.getInt("categoryId"));
        listing.setListingName(rs.getString("listingName"));
        listing.setListingTagLine(rs.getString("listingTagLine"));
        listing.setListingDescription(rs.getString("listingDescription"));
        listing.setListingImage(rs.getString("listingImage"));
        listing.setListingActive(rs.getBoolean("listingActive"));
        listing.setListingVerify(rs.getBoolean("listingVerify"));
        listing.setMinPrice(rs.getInt("minimumPrice"));
        listing.setMaxPrice(rs.getInt("maximumPrice"));
        listing.setEmail(rs.getString("email"));
        listing.setWebsite(rs.getString("website"));
        listing.setCategoryName(rs.getString("categoryName"));
        listing.setCategorySmallIcon(rs.getString("categorySmallIconURL"));
        listing.setCity(rs.getString("city"));
        return listing;
    }
}
