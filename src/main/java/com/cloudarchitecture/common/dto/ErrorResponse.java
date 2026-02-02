package com.cloudarchitecture.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;

    public static ResponseEntity<ErrorResponse> of(HttpStatus status, String message, String path) {
        return ResponseEntity.status(status).body(new ErrorResponse(status, message, path, LocalDateTime.now()));
    }
}
