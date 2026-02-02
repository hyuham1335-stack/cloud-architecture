package com.cloudarchitecture.member.dto.request;

import com.cloudarchitecture.member.entity.Member;
import lombok.Getter;

@Getter
public class CreateMemberRequest {
    private String name;
    private Long age;
    private String mbti;
}
