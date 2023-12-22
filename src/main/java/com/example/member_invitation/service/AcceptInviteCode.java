package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.type.MemberStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AcceptInviteCode {
    private final RedisServiceLettuce redisService;
    private final MemberRepository memberRepository;

    public String acceptInviteCode(String code) {
        try {
            String memberName = redisService.getStringOps(code);
            if (memberName == null) {
                return "Redis에서 해당 키에 대한 값이 존재하지 않습니다.";
            }

            boolean isRemoved = redisService.removeValue(code);
            if (!isRemoved) {
                return "Redis 삭제 작업 실패";
            }

            Member member = memberRepository.findByName(memberName)
                    .orElseThrow(() -> new EntityNotFoundException("Entity not found with name: " + memberName));

            member.setStatus(MemberStatus.ACTIVATE);
            memberRepository.save(member);
            return "초대 코드 처리 완료";
        } catch (Exception e) {
            // 로깅
            return "초대 코드 처리 중 오류 발생: " + e.getMessage();
        }
    }
}
