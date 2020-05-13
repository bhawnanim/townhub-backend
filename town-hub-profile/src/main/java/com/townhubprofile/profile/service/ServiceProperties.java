package com.townhubprofile.profile.service;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
@Builder
@ConfigurationProperties(prefix = "db")
public class ServiceProperties {
    @NotNull
    public DbQueries dbQueries;

    @Data
    @Component
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DbQueries {
        private String getProfiles;
        private String getProfileById;
        private String getProfileByRole;
        private String addProfile;
        private String updateProfile;
        private String updateStatus;
        private String getProfileId;
        private String updatePassword;
        private String getLoginState;
    }
}
