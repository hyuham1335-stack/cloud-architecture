package com.cloudarchitecture.member.service;

import com.cloudarchitecture.common.exception.FileUploadFailedException;
import com.cloudarchitecture.member.dto.request.CreateMemberRequest;
import com.cloudarchitecture.member.dto.response.CreateMemberResponse;
import com.cloudarchitecture.member.dto.response.GetOneMemberResponse;
import com.cloudarchitecture.member.dto.response.ProfileImageDownloadUrlResponse;
import com.cloudarchitecture.member.dto.response.ProfileImageUploadResponse;
import com.cloudarchitecture.member.entity.Member;
import com.cloudarchitecture.member.exception.MemberNotFoundException;
import com.cloudarchitecture.member.repository.MemberRepository;
import io.awspring.cloud.s3.S3Template;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private static final Duration PRESIGNED_URL_EXPIRATION = Duration.ofDays(7);
    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public CreateMemberResponse createMember(CreateMemberRequest request) {
        Member member = new Member(request.getName(), request.getAge(), request.getMbti());
        memberRepository.save(member);

        return CreateMemberResponse.of(member);
    }

    @Transactional(readOnly = true)
    public GetOneMemberResponse getOneMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 멤버입니다.")
        );

        return GetOneMemberResponse.of(member);
    }

    @Transactional
    public ProfileImageUploadResponse uploadProfileImage(Long memberId, MultipartFile file) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 멤버입니다.")
        );

        try {
            String key = "uploads/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            s3Template.upload(bucket, key, file.getInputStream());

            return new ProfileImageUploadResponse(key);
        } catch (IOException e) {
            throw new FileUploadFailedException("파일 업로드 실패");
        }
    }

    @Transactional
    public ProfileImageDownloadUrlResponse getProfileImageDownloadUrl(String key, Long memberId) {

        memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 멤버입니다.")
        );

        URL PresignedUrl = s3Template.createSignedGetURL(bucket, key, PRESIGNED_URL_EXPIRATION);

        return new ProfileImageDownloadUrlResponse(PresignedUrl.toString());
    }
}
