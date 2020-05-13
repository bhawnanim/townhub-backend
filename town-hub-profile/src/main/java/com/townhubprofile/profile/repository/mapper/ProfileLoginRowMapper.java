package com.townhubprofile.profile.repository.mapper;

import com.townhubprofile.profile.model.Profile;
import com.townhubprofile.profile.model.ProfileForLoginState;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileLoginRowMapper implements RowMapper<ProfileForLoginState> {
    @Override
    public ProfileForLoginState mapRow(ResultSet rs, int i) throws SQLException {
        ProfileForLoginState profile=new ProfileForLoginState();
        profile.setId(rs.getInt("profileId"));
        profile.setUsername(rs.getString("userName"));
        profile.setRole(rs.getString("role"));
        profile.setProfileActive(rs.getBoolean("profileActive"));
        return profile;
    }
}
