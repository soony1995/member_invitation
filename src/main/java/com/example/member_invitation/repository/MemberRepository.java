package com.example.member_invitation.repository;

import com.example.member_invitation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Long은 primary key의 타입

    @Query("SELECT m FROM Member m WHERE m.name = :name")
    Optional<Member> findByName(String name);
}

