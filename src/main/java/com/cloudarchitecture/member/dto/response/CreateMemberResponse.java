package com.cloudarchitecture.member.dto.response;

import com.cloudarchitecture.member.dto.request.CreateMemberRequest;
import com.cloudarchitecture.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMemberResponse {
    private final Long id;
    private final String name;
    private final Long age;
    private final String mbti;

    public static CreateMemberResponse of(Member member) {
        return new CreateMemberResponse(member.getId(), member.getName(), member.getAge(), member.getMbti());
    }
}
