package com.ysjleader.weightlog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendEMailDTO {
    private String email;
    private String token;
}
