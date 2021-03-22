package com.ysjleader.weightlog.service;

import com.ysjleader.weightlog.dto.SendEMailDTO;
import com.ysjleader.weightlog.dto.VerifyEmailDTO;
import com.ysjleader.weightlog.dto.VerifyResultDTO;
import com.ysjleader.weightlog.dto.request.VerifyEmailRequestDTO;
import com.ysjleader.weightlog.event.VerifyEmailEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
@Service
public class VerifyEmailService {
    private final ApplicationEventPublisher eventPublisher;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final JwtService jwtService;
    private final RestTemplate restTemplate = new RestTemplate();

    public void sendEmail(VerifyEmailRequestDTO verifyEmailRequestDTO) {
//        VerifyEmailEvent verifyEmailEvent = new VerifyEmailEvent(this, new SendEMailDTO(verifyEmailRequestDTO.getEmail(), jwtService.create(verifyEmailRequestDTO)));
//        executorService.execute(() -> eventPublisher.publishEvent(verifyEmailEvent));
//        executorService.shutdown();
    }

    /**
     * 1. 이메일로 넘어온 토큰 검증
     * 2. Redirect URL 에 검증 결과 보내줌
     * 3. Home URL 리턴해서 이메일 인증 누른거 리다이렉트 시켜주기
     * @param token
     * @return
     */
    public String verifyToken(String token) {
        String homeURL = "";
        try{
            VerifyEmailDTO verifyEmailDTO = jwtService.verifyEmail(token);
            redirect(verifyEmailDTO);
            homeURL = verifyEmailDTO.getHomeURL();
        } catch (IllegalArgumentException illegalArgumentException) {
            log.info(illegalArgumentException.getMessage());
        }
        return redirectHome(homeURL);
    }

    private void redirect(VerifyEmailDTO verifyEmailDTO) {
        try {
            restTemplate.postForEntity(verifyEmailDTO.getRedirectURL(), new VerifyResultDTO(verifyEmailDTO.getEmail()), String.class);
        }catch (Exception e) {
            log.info(e.getMessage());
        }

    }

    private String redirectHome(String homeURL) {
        return homeURL;
    }
}
