package com.cloudarchitecture.common.config;

import com.cloudarchitecture.common.dto.ErrorResponse;
import com.cloudarchitecture.common.exception.FileUploadFailedException;
import com.cloudarchitecture.member.exception.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> handleMemberException(MemberException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return ErrorResponse.of(e.getHttpStatus(), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(FileUploadFailedException.class)
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadFailedException e, HttpServletRequest request) {
        log.error(e.getMessage());

//        if(e.getCause() instanceof )

        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI());
    }

}
