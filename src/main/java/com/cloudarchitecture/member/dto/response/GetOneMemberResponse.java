package com.cloudarchitecture.member.dto.response;

import com.cloudarchitecture.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetOneMemberResponse {
    private final Long id;
    private final String name;
    private final Long age;
    private final String mbti;

    public static GetOneMemberResponse of(Member member){
        return new GetOneMemberResponse(member.getId(), member.getName(), member.getAge(), member.getMbti());
    }
}
