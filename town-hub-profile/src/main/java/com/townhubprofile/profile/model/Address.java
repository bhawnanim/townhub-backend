package com.townhubprofile.profile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Address {
    private int addressId;
    private String city;
    private String state;
    private String country;
    private String area;
    private String addressLine1;
    private String addressLine2;
    private int pinCode;
}
