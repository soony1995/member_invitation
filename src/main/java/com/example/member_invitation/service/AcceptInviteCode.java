package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.exception.InviteException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.type.ErrCode;
import com.example.member_invitation.type.MemberStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AcceptInviteCode {
    private final RedisServiceLettuce redisService;
    private final MemberRepository memberRepository;

    /*
    .orElseThrow(() -> new AccountException(ErrorCode.USER_NOT_FOUND));
     */
    public String acceptInviteCode(String code) {
            String memberName = redisService.getStringOps(code);
            if (memberName == null) {
                throw new InviteException(ErrCode.CODE_NOT_FOUND);
            }

            /*
            redis 삭제의 경우
            - 서버 연결 실패
            - 키가 존재하지 않을 떄
            - 권한 문제
            - 유효하지 않은 요청 등 각 상황 마다 다른 status code가 존재하는데 이때는
            redisException class를 만들어 관리를 하는지?
             */
            boolean isRemoved = redisService.removeValue(code);
            if (!isRemoved) {
                throw new InviteException(ErrCode.INTERNAL_SERVER_ERROR);
            }

            Member member = memberRepository.findByName(memberName)
                    .orElseThrow(() -> new InviteException(ErrCode.CODE_NOT_FOUND));

            member.setStatus(MemberStatus.ACTIVATE);
            memberRepository.save(member);
            return "초대 코드 처리 완료";
    }
}
