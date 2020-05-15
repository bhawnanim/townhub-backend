package com.townhubmailing.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class EmailModal {
    private String emailTo;
    private String subjact;
    private String text;
    private String ccEmail;
}
