package com.ysjleader.weightlog.service;

import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;

public interface SimpleMailService {
    void sendSignUpMail(SignUpRequestDTO signUpRequestDTO);
}
