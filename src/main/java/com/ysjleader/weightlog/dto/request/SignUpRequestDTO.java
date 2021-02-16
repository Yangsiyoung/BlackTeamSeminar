package com.ysjleader.weightlog.dto.request;

import com.ysjleader.weightlog.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDTO {

    @NotBlank @Size(min = 2, max = 12)
    private String userID;
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min=8, max=30)
    private String password;

    public Member toEntity() {
        return Member.builder().userID(userID).email(email).password(password).build();
    }
}
