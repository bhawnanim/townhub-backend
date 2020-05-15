package com.townhubprofile.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Contact {
    private int contactId;
    private String phoneNumber;
    private String email;
    private String workNumber;
}
