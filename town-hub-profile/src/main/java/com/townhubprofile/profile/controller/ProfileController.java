package com.townhubprofile.profile.controller;

import com.townhubprofile.profile.clients.AddressClient;
import com.townhubprofile.profile.clients.ContactClient;
import com.townhubprofile.profile.model.*;
import com.townhubprofile.profile.service.ProfileService;
import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping(path = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @Autowired
    AddressClient addressClient;

    @Autowired
    ContactClient contactClient;

    @GetMapping("/")
    public ResponseEntity<Result<List<Profile>>> getAllProfile() {
        Result<List<Profile>> result = profileService.findAllProfiles();
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<Profile>> getProfileById(@PathVariable("id") int id)
            throws Exception {
        Result<Profile> result = profileService.findProfilesById(id);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<Result<List<Profile>>> getAllProfilesByRole(
            @PathVariable("role") @Valid @Pattern(regexp = "(user|business|admin)") String role) throws Exception {
        Result<List<Profile>> result = profileService.findAllProfilesByRole(role);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @PostMapping("/")
    public ResponseEntity<Result<ProfileWithPassword>> addProfile(
            @RequestBody(required = true) @Valid ProfileWithPassword profileWithPassword) throws Exception {
        profileService.checkUsername(profileWithPassword.getUserName());
        ResponseEntity<Result<Integer>> res = addressClient.saveAddress(profileWithPassword.getAddress());
        if(res.getStatusCodeValue()==201){
            profileWithPassword.setAddressId(res.getBody().getData().intValue());
        }else {
            throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays
                    .asList(new Result.TownHubError(profileWithPassword.hashCode(), "unable to add the given profile")))));
        }

        ResponseEntity<Result<Integer>> res2 = contactClient.saveContact(profileWithPassword.getContact());
        if(res2.getStatusCodeValue()==201){
            profileWithPassword.setContactId(res.getBody().getData().intValue());
        }else {
            throw new ResultException(new Result<>(400, "Error!, please try again!", new ArrayList<>(Arrays
                    .asList(new Result.TownHubError(profileWithPassword.hashCode(), "unable to add the given profile")))));
        }
        Result<ProfileWithPassword> result = profileService.addProfile(profileWithPassword);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Profile>> updateProfile(@PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
                                                         @RequestBody(required = true) @Valid Profile profile) throws Exception {
        Result<Profile> result = profileService.updateProfile(id, profile);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Result<String>> updateStatus(@PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
                                                       @PathVariable("status") @Valid @Pattern(regexp = "(true|false)") boolean status) throws Exception {
        Result<String> result = profileService.updateStatus(id, status);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @PutMapping("/{id}/images/")
    public ResponseEntity<Result<String>> updateProfileImage(@PathVariable("id") @Valid @Pattern(regexp = "[0-9]*") int id,
                                                             @RequestBody(required = true) ProfileImageUpload profileImageUpload) throws Exception {
        Result<String> result = profileService.updateProfileImage(id,profileImageUpload.getProfileImage());
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @PutMapping("/")
    public ResponseEntity<Result<String>> updatePassword(
            @RequestBody(required = true) @Valid ProfileWithNewPassword profileWithNewPassword) throws Exception {
        Result<String> result = profileService.updatePassword(profileWithNewPassword);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Result<ProfileForLoginState>> authenticate(
            @RequestBody(required = true) @Valid ProfileAtLogin profileAtLogin) throws Exception {
        Result<ProfileForLoginState> result = profileService.authenticate(profileAtLogin);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getstatus()));
    }
}
