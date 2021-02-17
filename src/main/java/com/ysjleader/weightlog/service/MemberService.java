package com.ysjleader.weightlog.service;

import com.ysjleader.weightlog.domain.MemberRepository;
import com.ysjleader.weightlog.dto.SendEMailDTO;
import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import com.ysjleader.weightlog.dto.response.SignUpResponseDTO;
import com.ysjleader.weightlog.event.VerifyEmailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO 1. Email 보내기
 *          받는 데이터 : Email, Redirect URI, Home URI
 *          메일 내용 : JWT 들어간 URL(ex. http://localhost:8090/email/auth?token=ADFASDKFKSAMVASIFAKSDFKASDF)
 *          JWT 에 들어갈 데이터(payload) : 유저 식별자(email), home_uri, redirect_uri
 *       2. Email 인증
 *          JWT 검증
 *              1. JWT 복호화 - 자동으로 암호키 검증 됨(대칭키)
 *          Email 인증
 *              JWT - payload - sub : email, redirect_uri : "redirect_uri"
 *          Redirect URI 에 다가 {"email" : "ysjlaeder@gmail.com"}
 *          리턴 : "redirect:" + home_uri
 *
 *
 *          1. JwtService
 *
 *
 *
 *          */
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        executorService.execute(() -> eventPublisher.publishEvent(new VerifyEmailEvent(this, new SendEMailDTO(signUpRequestDTO.getEmail(), signUpRequestDTO.getEmail()))));
        executorService.shutdown();
        return SignUpResponseDTO.fromEntity(memberRepository.save(signUpRequestDTO.toEntity()));
    }
}
