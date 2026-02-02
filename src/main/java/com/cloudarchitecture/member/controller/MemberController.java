package com.cloudarchitecture.member.controller;

import com.cloudarchitecture.member.dto.request.CreateMemberRequest;
import com.cloudarchitecture.member.dto.response.CreateMemberResponse;
import com.cloudarchitecture.member.dto.response.GetOneMemberResponse;
import com.cloudarchitecture.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<CreateMemberResponse> createMember(@RequestBody CreateMemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(request));
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<GetOneMemberResponse> getOneMember(@PathVariable Long memberId){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getOneMember(memberId));
    }
}
