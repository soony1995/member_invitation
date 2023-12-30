package com.example.member_invitation.service;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.member_invitation.domain.Member;
import com.example.member_invitation.exception.MemberException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.type.ErrCode;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Optional<Member> getMember(Long id) {
        return findMember(id);
    }

    private Optional<Member> findMember(Long id) {
        if (id < 1) {
            throw new MemberException(ErrCode.BAD_REQUEST);
        }
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new MemberException(ErrCode.ACCOUNT_NOT_FOUND);
        }
        return member;
    }
}
