package com.townhubutils.contact.service;

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
public class ContactProperties {

    @NotNull
    public ContactProperties.DbQueries dbQueries;

    @Data
    @Component
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DbQueries {
        private String insertContact;
        private String updateContact;
        private String getContactById;
    }
}
