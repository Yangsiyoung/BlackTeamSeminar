package com.ysjleader.weightlog.web;

import com.ysjleader.weightlog.dto.SendEMailDTO;
import com.ysjleader.weightlog.dto.request.VerifyEmailRequestDTO;
import com.ysjleader.weightlog.dto.response.SignUpResponseDTO;
import com.ysjleader.weightlog.event.VerifyEmailEvent;
import com.ysjleader.weightlog.service.VerifyEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequestMapping("/verify/email")
@RequiredArgsConstructor
@Controller
public class VerifyEmailController {

    private final VerifyEmailService verifyEmailService;

    @ResponseBody
    @PostMapping
    public ResponseEntity verifyEmail(@RequestBody VerifyEmailRequestDTO verifyEmailRequestDTO) {
        verifyEmailService.sendEmail(verifyEmailRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public String verifyEmail(@RequestParam("token") String token) {
        return "redirect:" + verifyEmailService.verifyToken(token);
    }
}
