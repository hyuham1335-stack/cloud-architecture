package com.cloudarchitecture.member.service;

import com.cloudarchitecture.member.dto.request.CreateMemberRequest;
import com.cloudarchitecture.member.dto.response.CreateMemberResponse;
import com.cloudarchitecture.member.dto.response.GetOneMemberResponse;
import com.cloudarchitecture.member.entity.Member;
import com.cloudarchitecture.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public CreateMemberResponse createMember(CreateMemberRequest request) {
        Member member = new Member(request.getName(), request.getAge(), request.getMbti());
        memberRepository.save(member);

        return CreateMemberResponse.of(member);
    }

    @Transactional(readOnly = true)
    public GetOneMemberResponse getOneMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 멤버입니다.")
        );

        return GetOneMemberResponse.of(member);
    }
}
