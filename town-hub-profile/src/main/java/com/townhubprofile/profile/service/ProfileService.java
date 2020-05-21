package com.townhubprofile.profile.service;

import com.townhubprofile.profile.clients.AddressClient;
import com.townhubprofile.profile.clients.ContactClient;
import com.townhubprofile.profile.model.*;
import com.townhubprofile.profile.repository.ProfileRepository;
import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    @Qualifier("profileRepo")
    ProfileRepository profileRepository;

    public Result<List<Profile>> findAllProfiles() {
        List<Profile> list = profileRepository.findAllProfiles();
        if (list.size() > 0) {
            return new Result<>(200, list);
        }
        throw new ResultException(new Result<>(404, "no profile's found, please try again!",
                new ArrayList<>(Arrays.asList(new Result.TownHubError(404,"No Profile Found, Please Try Again Later")))));
    }

    public Result<Profile> findProfilesById(int id) throws Exception {
        List<Profile> list = profileRepository.findProfileById(id);
        if (list.size() > 0) {
            return new Result<>(200, list.get(0));
        }
        throw new ResultException(new Result<>(404, "no profile's found, please try again!",
                new ArrayList<>(Arrays.asList(new Result.TownHubError((id + "").hashCode(),
                        "profile with given id('" + id + "') does not exists")))));
    }

    public Result<List<Profile>> findAllProfilesByRole(String role) throws Exception {
        List<Profile> list = profileRepository.findAllProfilesByRole(role);
        if (list.size() > 0) {
            return new Result<>(200, list);
        }
        throw new ResultException(new Result<>(404, "no profile's found, please try again!",
                new ArrayList<>(Arrays.asList(new Result.TownHubError(role.hashCode(),
                        "profile with given role('" + role + "') does not exists")))));
    }

    public Result<ProfileWithPassword> addProfile(ProfileWithPassword profileWithPassword) throws Exception {
        int id = profileRepository.addProfile(profileWithPassword);
        profileWithPassword.setProfileId(id);
        if (id > 0) {
            return new Result<>(201, new ProfileWithPassword());
        }
        throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays
                .asList(new Result.TownHubError(profileWithPassword.hashCode(), "unable to add the given profile")))));
    }

    public Result<Profile> updateProfile(int id, Profile profile) throws Exception {
        if (profileRepository.updateProfile(id, profile)) {
            return new Result<>(200, (Profile) profile);
        }
        throw new ResultException(new Result<>(400, "Unable to update the given profile, please try again!",
                new ArrayList<>(Arrays.asList(new Result.TownHubError(profile.hashCode(),
                        "given profileId('" + id + "') does not exists ")))));
    }

    public Result<String> updateStatus(int id, boolean status) throws Exception {
        if (profileRepository.updateStatus(id, status)) {
            return new Result<>(200, "status of given id(" + id + ") has been succefully updated to '" + status + "'");
        }
        throw new ResultException(new Result<>(400, "Unable to update the given profile, please try again!",
                new ArrayList<>(Arrays.asList(new Result.TownHubError((id + "").hashCode(),
                        "given profileId('" + id + "') does not exists ")))));
    }

    public Result<String> updatePassword(ProfileWithNewPassword profileWithNewPassword) throws Exception {
        int result = profileRepository.updatePassword(profileWithNewPassword);
        if (result > 0) {
            return new Result<>(200, "password has been succefully updated");
        } else if (result == -1) {
            throw new ResultException(new Result<>(409, "old password does not match!"));
        } else {
            throw new ResultException(new Result<>(400, "Error!, please try again!",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError("updatePassword".hashCode(),
                            "unable to update the password!")))));
        }
    }

    public Result<ProfileForLoginState> authenticate(ProfileAtLogin profileAtLogin) throws Exception {
        List<ProfileForLoginState> profileForLoginState = profileRepository.authenticate(profileAtLogin);
        if (profileForLoginState != null) {
            if (!profileForLoginState.get(0).getProfileActive()) {
                return new Result<>(200, "Sorry! you have been blocked by the admin");
            } else {
                return new Result<>(200, profileForLoginState.get(0));
            }
        }
        throw new ResultException(new Result<>(400, "invalid username or password"));
    }

    public Result<Integer> checkUsername(String username) throws Exception{
        int profileId=profileRepository.checkUsername(username);
        if(profileId>0){
            throw new ResultException(new Result<>(409, "Username Already taken Please select some other username"));
        }else {
            return new Result<>(200, profileId);
        }
    }
}