package com.example.member_invitation.service;

import com.example.member_invitation.domain.Invitation;
import com.example.member_invitation.domain.Member;
import com.example.member_invitation.dto.CreateInviteCode;
import com.example.member_invitation.exception.InviteException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.repository.RedisRepository;
import com.example.member_invitation.type.ErrCode;
import com.example.member_invitation.type.MemberStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final MemberRepository memberRepository;
    private final RedisRepository redisRepository;

    @Transactional
    public String createInviteCode(CreateInviteCode.Request dto) {
        checkValidMember(dto);
        saveTmpMember(dto);
        String code = new Invitation().createCode();
        // Code - MemberName
        saveInviteCode(dto, code);
        return code;
    }

    private void checkValidMember(CreateInviteCode.Request dto) {
        if (memberRepository.findByName(dto.getInviteMemberName()).isPresent()) {
            throw new InviteException(ErrCode.ACCOUNT_ALREADY_REGISTERED);
        }
    }

    private void saveInviteCode(CreateInviteCode.Request dto, String code) {
        redisRepository.setValue(code, dto.getInviteMemberName(), Duration.ofDays(1));
    }

    private void saveTmpMember(CreateInviteCode.Request dto) {
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
