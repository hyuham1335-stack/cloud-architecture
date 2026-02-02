package com.cloudarchitecture.member.controller;

import com.cloudarchitecture.member.dto.request.CreateMemberRequest;
import com.cloudarchitecture.member.dto.response.CreateMemberResponse;
import com.cloudarchitecture.member.dto.response.GetOneMemberResponse;
import com.cloudarchitecture.member.dto.response.ProfileImageDownloadUrlResponse;
import com.cloudarchitecture.member.dto.response.ProfileImageUploadResponse;
import com.cloudarchitecture.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<CreateMemberResponse> createMember(@RequestBody CreateMemberRequest request) {
        log.info("[API - LOG] 멤버 생성 요청");
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(request));
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<GetOneMemberResponse> getOneMember(@PathVariable Long memberId) {
        log.info("[API - LOG] 멤버 조회 요청");
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getOneMember(memberId));
    }

    @PostMapping("/api/members/{memberId}/profile-image")
    public ResponseEntity<ProfileImageUploadResponse> uploadProfileImage(
            @PathVariable Long memberId,
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.uploadProfileImage(memberId, file));
    }

    @GetMapping("/api/members/{memberId}/profile-image")
    public ResponseEntity<ProfileImageDownloadUrlResponse> getProfileImageDownloadUrl(@RequestParam String key){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getProfileImageDownloadUrl(key));
    }
}
