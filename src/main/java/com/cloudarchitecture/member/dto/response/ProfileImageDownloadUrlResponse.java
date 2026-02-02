package com.cloudarchitecture.member.dto.response;

import lombok.Getter;

@Getter
public class ProfileImageDownloadUrlResponse {
    private final String url;

    public ProfileImageDownloadUrlResponse(String url) {
        this.url = url;
    }
}
