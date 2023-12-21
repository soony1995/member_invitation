package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class InvitationService {
    private final MemberRepository memberRepository;

    @Transactional
        public Member getMember(Long id){
            if (id < 0){
                throw new RuntimeException("member id can't be zero value");
            }
            return memberRepository.findById(id).get();
        }
}
