package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional // 굳이?
    public Member getMember(Long id){
        if (id < 0){
            throw new RuntimeException("member id can't be zero value");
        }
        return memberRepository.findById(id).get();
    }
}
