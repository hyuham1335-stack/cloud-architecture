package com.cloudarchitecture.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberNotFoundException extends MemberException {
    public MemberNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
