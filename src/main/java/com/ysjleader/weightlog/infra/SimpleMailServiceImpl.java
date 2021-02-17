package com.ysjleader.weightlog.infra;

import com.ysjleader.weightlog.dto.SendEMailDTO;
import com.ysjleader.weightlog.service.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class SimpleMailServiceImpl implements SimpleMailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${email.auth.url}")
    private String authURL;

    @Override
    public void sendSignUpMail(SendEMailDTO sendEMailDTO) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("[Black] 이메일 인증을 해주세요!");
        helper.setTo(sendEMailDTO.getEmail());
        Context context = new Context();
        context.setVariable("url", authURL + sendEMailDTO.getToken());
        String template = springTemplateEngine.process("verify_mail_template", context);
        helper.setText(template, true);
        helper.setFrom(senderEmail);
        javaMailSender.send(message);
    }
}
