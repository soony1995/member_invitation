package com.example.member_invitation.service;

import com.example.member_invitation.domain.Invitation;
import com.example.member_invitation.domain.Member;
import com.example.member_invitation.dto.CreateInviteCode;
import com.example.member_invitation.exception.InviteException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.type.ErrCode;
import com.example.member_invitation.type.MemberStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Transactional
@RequiredArgsConstructor
public class InvitationService {
    private final RedisServiceLettuce redisService;
    private final MemberRepository memberRepository;

    public String createInviteCode(CreateInviteCode.Request dto) {
        checkAccountExist(dto);
        createTmpAccount(dto);
        String code = new Invitation().createCode();
        // <Code - MemberName>
        setInviteCode(dto, code);
        return code;
    }

    private void setInviteCode(CreateInviteCode.Request dto, String code) {
        redisService.setStringOps(code, dto.getInviteMemberName(),Duration.ofDays(1));
    }

    private void checkAccountExist(CreateInviteCode.Request dto) {
        memberRepository.findByName(dto.getInviteMemberName())
                .ifPresent(member -> {
                    throw new InviteException(ErrCode.ACCOUNT_ALREADY_REGISTERED);
                });
    }

    private void createTmpAccount(CreateInviteCode.Request dto) {
        memberRepository.save(
                Member.builder()
                        .name(dto.getInviteMemberName())
                        .phoneNumber(dto.getInviteMemberPhoneNumber())
                        .email(dto.getInviteMemberEmail())
                        .status(MemberStatus.DEACTIVATE)
                        .build()
        );
    }
}
