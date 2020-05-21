package com.townhubprofile.profile.repository;

import com.townhubprofile.profile.model.*;
import com.townhubprofile.profile.repository.mapper.ProfileLoginRowMapper;
import com.townhubprofile.profile.repository.mapper.ProfileRowMapper;
import com.townhubprofile.profile.service.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository(value = "profileRepo")
public class ProfileRepositoryImpl implements ProfileRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ServiceProperties serviceProperties;
    @Override
    public List<Profile> findAllProfiles() {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetProfiles(), new ProfileRowMapper());
    }

    @Override
    public List<Profile> findProfileById(int id) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetProfileById(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,id);
            }
        }, new ProfileRowMapper());
    }

    @Override
    public List<Profile> findAllProfilesByRole(String role) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetProfileByRole(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,role);
            }
        }, new ProfileRowMapper());
    }

    @Override
    public int addProfile(ProfileWithPassword profile) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getAddProfile(),profile.getUserName(),profile.getFirstName(),profile.getLastName(),profile.getPassword(),profile.getAddressId(),profile.getContactId(),profile.getProfileActive(),profile.getProfileImage(),profile.getRole());

    }

    @Override
    public boolean updateProfile(int id, Profile profile) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getUpdateProfile(),profile.getUserName(),profile.getFirstName(),profile.getLastName(),profile.getProfileImage(),profile.getRole(),id)>0;
    }

    @Override
    public boolean updateStatus(int id, boolean status) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getUpdateStatus(),status,id)>0;

    }

    @Override
    public int updatePassword(ProfileWithNewPassword profileWithNewPassword) throws Exception {
        if (jdbcTemplate.query(serviceProperties.getDbQueries().getGetProfileId(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,profileWithNewPassword.getId());
                preparedStatement.setString(2,profileWithNewPassword.getOldPassword());
            }
        }, new ProfileRowMapper()).size()>0) {
            return jdbcTemplate.update(serviceProperties.getDbQueries().getUpdatePassword(), profileWithNewPassword.getNewPassword(),profileWithNewPassword.getId());
        }
        return -1;    }

    @Override
    public List<ProfileForLoginState> authenticate(ProfileAtLogin profileAtLogin) throws Exception {
        List<ProfileForLoginState> list = jdbcTemplate.query(serviceProperties.getDbQueries().getGetLoginState(), new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,profileAtLogin.getUsername());
                preparedStatement.setString(2,profileAtLogin.getPassword());
            }
        },new ProfileLoginRowMapper());
        if (list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public int checkUsername(String username) throws Exception {

        Map<String, Object> result = jdbcTemplate.queryForMap(serviceProperties.getDbQueries().getGetCheckUsername(),username);
        return Integer.parseInt(result.get("ProfileId").toString());
    }
}
