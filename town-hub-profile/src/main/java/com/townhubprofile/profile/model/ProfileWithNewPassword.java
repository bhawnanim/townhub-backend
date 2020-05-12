package com.townhubprofile.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProfileWithNewPassword {
    private int id;
    private String oldPassword;
    private String newPassword;
}
