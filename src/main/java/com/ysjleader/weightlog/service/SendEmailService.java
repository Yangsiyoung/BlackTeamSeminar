package com.ysjleader.weightlog.service;

import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import com.ysjleader.weightlog.event.SignUpEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SendEmailService {

    /**
     * DIP 적용
     * 바로 JavaMailSender 바라보기보다 SendMailService 에서 필요한 기능인
     * 회원가입 메일 보내기라는 목적에 맞춰서 SimpleMailService 인터페이스를 구상하고
     * SimpleMailService 를 상속받은 SimpleMailServiceImpl 구현체를 만들어
     * 거기서 JavaMailSender 라는 기술 구현체를 사용함으로써
     * 고수준 모듈 -> 저수준 모듈로 의존하는 것이 아니라
     * 저수준 모듈 -> 고수준 모듈로 의존하게 함
     * 자세한 내용은 (https://github.com/Yangsiyoung/DDD) 의 chapter02 참조
     */
    private final SimpleMailService simpleMailService;

    @EventListener(SignUpEvent.class)
    public void sendSignUpEmail(ApplicationEvent event) {
        SignUpEvent signUpEvent = (SignUpEvent) event;
        SignUpRequestDTO signUpRequestDTO = signUpEvent.getSignUpRequestDTO();
        log.info("##### SEND SIGNUP EMAIL#####");
        log.info("User ID is {}", signUpRequestDTO.getUserID());
        simpleMailService.sendSignUpMail(signUpRequestDTO);
    }
}
