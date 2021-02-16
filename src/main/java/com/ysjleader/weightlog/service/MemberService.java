package com.ysjleader.weightlog.service;

import com.ysjleader.weightlog.domain.MemberRepository;
import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import com.ysjleader.weightlog.dto.response.SignUpResponseDTO;
import com.ysjleader.weightlog.event.SignUpEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        executorService.execute(() -> eventPublisher.publishEvent(new SignUpEvent(this, signUpRequestDTO)));
        executorService.shutdown();
        return SignUpResponseDTO.fromEntity(memberRepository.save(signUpRequestDTO.toEntity()));
    }
}
