package com.ysjleader.weightlog.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ysjleader.weightlog.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDTO {
    private Long id;
    private String userID;
    private String email;

    @QueryProjection
    public MemberDTO(Long id, String userID, String email) {
        this.id = id;
        this.userID = userID;
        this.email = email;
    }
}
