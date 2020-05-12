package com.townhubzuul.profileRepository;

import com.townhubzuul.model.ProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProfileRepository {

    private String tableName = "profile";

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProfileModel> getAllProfiles() {
        return jdbcTemplate.query("select * from " + tableName, new RowMapper<ProfileModel>() {
            @Override
            public ProfileModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProfileModel model = new ProfileModel();
                model.setId(rs.getInt("profileId"));
                model.setUsername(rs.getString("userName"));
                model.setPassword(encoder.encode(rs.getString("password")));
                model.setRole(rs.getString("role"));
                model.setStatus(rs.getBoolean("profileActive"));
                return model;
            }
        });
    }

    public ProfileModel getProfileByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from " + tableName + " where userName='" + username + "'",
                new RowMapper<ProfileModel>() {
                    @Override
                    public ProfileModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ProfileModel model = new ProfileModel();
                        model.setId(rs.getInt("profileId"));
                        model.setUsername(rs.getString("userName"));
                        model.setPassword(encoder.encode(rs.getString("password")));
                        model.setRole(rs.getString("role"));
                        model.setStatus(rs.getBoolean("profileActive"));
                        return model;
                    }
                });
    }
}
