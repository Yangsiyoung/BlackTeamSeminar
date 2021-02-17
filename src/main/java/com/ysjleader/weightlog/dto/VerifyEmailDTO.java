package com.ysjleader.weightlog.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class VerifyEmailDTO {
    private String email;
    private String redirectURL;
    private String homeURL;

    @Builder
    public VerifyEmailDTO(String email, String redirectURL, String homeURL) {
        this.email = email;
        this.redirectURL = redirectURL;
        this.homeURL = homeURL;
    }
}
