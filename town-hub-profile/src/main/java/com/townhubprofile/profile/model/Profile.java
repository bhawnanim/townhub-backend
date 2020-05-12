package com.townhubprofile.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Profile {
    private int profileId;
    private String userName;
    private String firstName;
    private String lastName;
    private int addressId;
    private int contactId;
    private Boolean profileActive;
    private String profileImage;
    private String role;
}
