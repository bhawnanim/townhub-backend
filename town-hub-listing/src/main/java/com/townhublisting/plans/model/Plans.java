package com.townhublisting.plans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Plans {
    private int planId;
    private String planName;
    private String planAmount;
    private String planDescription;
    private boolean planActive;
    private int planListingLimit;
    private String planDuration;
    private boolean reviewShow;
    private boolean contactShow;
}
