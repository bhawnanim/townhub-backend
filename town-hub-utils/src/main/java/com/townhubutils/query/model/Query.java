package com.townhubutils.query.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Query {
    private int queryId;
    private int listingId;
    private String firstName;
    private String lastName;
    private String title;
    private String message;
    private String phoneNumber;
    private String email;
}
