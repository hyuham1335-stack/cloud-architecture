package com.cloudarchitecture.member.repository;

import com.cloudarchitecture.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
