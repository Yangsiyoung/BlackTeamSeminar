package com.ysjleader.weightlog.web;

import com.ysjleader.weightlog.dto.request.SignUpRequestDTO;
import com.ysjleader.weightlog.dto.response.SignUpResponseDTO;
import com.ysjleader.weightlog.exception.BadInputException;
import com.ysjleader.weightlog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO, Errors errors) throws BadInputException {
        checkRequestDTO(errors);
        return ResponseEntity.ok(memberService.signUp(signUpRequestDTO));
    }

    private void checkRequestDTO(Errors errors) throws BadInputException {
        if(errors.hasErrors()) {
            errors.getAllErrors().forEach(objectError -> log.info(objectError.getDefaultMessage()));
            throw new BadInputException("입력값이 올바르지 않습니다.");
        }
    }
}
