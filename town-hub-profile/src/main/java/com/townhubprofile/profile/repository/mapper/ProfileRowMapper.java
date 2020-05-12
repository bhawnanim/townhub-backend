package com.townhubprofile.profile.repository.mapper;

import com.townhubprofile.profile.model.Profile;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet rs, int i) throws SQLException {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profileId"));
        profile.setUserName(rs.getString("userName"));
        profile.setFirstName(rs.getString("firstName"));
        profile.setLastName(rs.getString("lastName"));
        profile.setAddressId(rs.getInt("addressId"));
        profile.setContactId(rs.getInt("contactId"));
        profile.setProfileActive(rs.getBoolean("profileActive"));
        profile.setProfileImage(rs.getString("profileImage"));
        profile.setRole(rs.getString("role"));
        return profile;
    }
}
