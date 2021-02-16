package com.ysjleader.weightlog.web;

import com.ysjleader.weightlog.exception.BadInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(BadInputException.class)
    public ResponseEntity handleException(Exception exception) {
        log.error("error", exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
