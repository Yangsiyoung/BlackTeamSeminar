package com.ysjleader.weightlog.service;

import com.ysjleader.weightlog.dto.SendEMailDTO;

import javax.mail.MessagingException;

public interface SimpleMailService {
    void sendSignUpMail(SendEMailDTO sendEMailDTO) throws MessagingException;
}
