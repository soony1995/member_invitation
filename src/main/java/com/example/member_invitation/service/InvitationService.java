package com.example.member_invitation.service;

import com.example.member_invitation.domain.Invitation;
import com.example.member_invitation.domain.Member;
import com.example.member_invitation.dto.CreateInviteCode;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.type.MemberStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class InvitationService {
    private final RedisServiceLettuce redisService;
    private final MemberRepository memberRepository;

    public String createInviteCode(CreateInviteCode.Request dto) {
        // TODO: 이미 회원이 그룹에 속해 있는 지 확인하는 로직 필요
        // 회원 임시 생성
        memberRepository.save(
                Member.builder()
                        .name(dto.getInviteMemberName())
                        .phoneNumber(dto.getInviteMemberPhoneNumber())
                        .email(dto.getInviteMemberEmail())
                        .status(MemberStatus.DEACTIVATE)
                        .build()
        );
        // 초대 코드 생성
        String code = new Invitation().createCode();
        // 초대 코드 저장

        // Code - MemberName
        redisService.setStringOps(code,dto.getInviteMemberName(),Duration.ofDays(1));
        return code;
    }
}
