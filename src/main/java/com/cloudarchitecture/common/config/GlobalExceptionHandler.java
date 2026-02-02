package com.cloudarchitecture.common.config;

import com.cloudarchitecture.common.dto.ErrorResponse;
import com.cloudarchitecture.member.exception.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> handleMemberException(MemberException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.of(e.getHttpStatus(), e.getMessage(), request.getRequestURI()));
    }

}
