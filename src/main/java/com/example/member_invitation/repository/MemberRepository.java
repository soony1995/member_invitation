package com.example.member_invitation.repository;

import com.example.member_invitation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    // Long은 primary key의 타입
}
