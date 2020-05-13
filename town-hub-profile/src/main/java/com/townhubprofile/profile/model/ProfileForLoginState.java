package com.townhubprofile.profile.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProfileForLoginState {
    private int id;
    private String username;
    private String role;
    private Boolean profileActive;
}
