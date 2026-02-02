package com.cloudarchitecture.member.dto.response;

import lombok.Getter;

@Getter
public class ProfileImageUploadResponse {
    private final String key;

    public ProfileImageUploadResponse(String key) {
        this.key = key;
    }
}
