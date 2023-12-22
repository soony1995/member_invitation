package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional // 굳이?
    public Optional<Member> getMember(Long id){
        if (id < 0){
            throw new RuntimeException("member id can't be zero value");
        }
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()){
            throw new EntityNotFoundException("Member not found with ID: " + id);
        }
        return member;
    }
}
