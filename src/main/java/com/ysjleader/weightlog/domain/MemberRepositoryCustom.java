package com.ysjleader.weightlog.domain;

import com.ysjleader.weightlog.dto.MemberDTO;

public interface MemberRepositoryCustom {
    MemberDTO search(Long id);
}
