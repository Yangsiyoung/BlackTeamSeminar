package com.ysjleader.weightlog.infra;

import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import com.ysjleader.weightlog.service.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SimpleMailServiceImpl implements SimpleMailService {

    private final JavaMailSender javaMailSender;
    private static final String SENDER_EMAIL = "ysjleader@gmail.com";
    @Override
    public void sendSignUpMail(SignUpRequestDTO signUpRequestDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpRequestDTO.getEmail());
        message.setFrom(SENDER_EMAIL);
        message.setSubject("Weight Log 회원가입을 축하드립니다!");
        message.setText(signUpRequestDTO.getUserID() + "님 매일매일 힘내서 함께해보아요!!");
        javaMailSender.send(message);
    }
}
