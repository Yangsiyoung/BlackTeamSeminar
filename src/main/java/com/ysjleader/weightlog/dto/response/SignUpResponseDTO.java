package com.ysjleader.weightlog.dto.response;

import com.ysjleader.weightlog.domain.Member;
import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class SignUpResponseDTO {
    private String userID;
    private String email;
    private String password;

    public static SignUpResponseDTO fromEntity(Member member) {
        return new SignUpResponseDTO(member.getUserID(), member.getEmail(), member.getPassword());
    }
}
