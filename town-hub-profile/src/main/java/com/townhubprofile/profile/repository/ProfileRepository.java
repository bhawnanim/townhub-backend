package com.townhubprofile.profile.repository;

import com.townhubprofile.profile.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository {
    public List<Profile> findAllProfiles();

    public List<Profile> findProfileById(int id) throws Exception;

    public List<Profile> findAllProfilesByRole(String role) throws Exception;

    public int addProfile(ProfileWithPassword profile) throws Exception;

    public boolean updateProfile(int id, Profile profile) throws Exception;

    public boolean updateStatus(int id, boolean status) throws Exception;

    public boolean updateProfileImage(int id, String profileImage) throws Exception;

    public int updatePassword(ProfileWithNewPassword profileWithNewPassword) throws Exception;

    public List<ProfileForLoginState> authenticate(ProfileAtLogin profileAtLogin) throws Exception;

    public int checkUsername(String username) throws Exception;
}
