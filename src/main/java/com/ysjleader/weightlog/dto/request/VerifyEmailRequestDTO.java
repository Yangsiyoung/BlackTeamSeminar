package com.ysjleader.weightlog.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyEmailRequestDTO {
    private String email;
    private String redirectURL;
    private String homeURL;

}
