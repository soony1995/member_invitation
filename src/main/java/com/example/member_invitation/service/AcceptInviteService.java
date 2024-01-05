package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.exception.InviteException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.repository.RedisRepository;
import com.example.member_invitation.type.ErrCode;
import com.example.member_invitation.type.MemberStatus;
import com.example.member_invitation.type.Response;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptInviteService {
    private final RedisRepository redisRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String acceptInviteCode(String code) {
        String memberName = checkValidCode(code);
        expireInviteCode(code);
        newMember(getMember(memberName));
        return Response.SUCCESS_CREATE_CODE.getDescription();
    }

    private void newMember(Member member) {
        memberRepository.save(Member.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .status(MemberStatus.ACTIVATE)
                .build());
    }

    private Member getMember(String memberName) {
        return memberRepository.findByName(memberName)
                .orElseThrow(() -> new InviteException(ErrCode.ACCOUNT_NOT_FOUND));
    }

    private void expireInviteCode(String code) {
        if (!redisRepository.removeValue(code)) {
            throw new InviteException(ErrCode.INTERNAL_SERVER_ERROR);
        }
    }

    private String checkValidCode(String code) {
        String memberName = redisRepository.getValue(code);
        // isEmpty는 값이 null인지 확인해 주지 않기 때문에 memberName이 null 이라면 nullPointerException이 발생한다.
        if (memberName == null || memberName.isEmpty()) {
            throw new InviteException(ErrCode.CODE_NOT_FOUND);
        }
        return memberName;
    }
}
