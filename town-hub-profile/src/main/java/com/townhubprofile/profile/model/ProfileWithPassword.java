package com.townhubprofile.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProfileWithPassword extends Profile {
    private String password;
    private Address address;
    private Contact contact;
}
