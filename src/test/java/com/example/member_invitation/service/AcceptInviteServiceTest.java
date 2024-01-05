package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.exception.InviteException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.repository.RedisRepository;
import com.example.member_invitation.type.ErrCode;
import com.example.member_invitation.type.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.Name;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AcceptInviteServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private RedisRepository redisRepository;

    @InjectMocks
    private AcceptInviteService acceptInviteService;

    private final String validCode = "code";
    private final String memberName = "sooncheol";


    @Test
    public void GOOD() {

        //when
        when(redisRepository.getValue(validCode)).thenReturn(memberName);
        when(memberRepository.findByName(memberName)).thenReturn(Optional.of(new Member()));
        when(redisRepository.removeValue(validCode)).thenReturn(true);

        String res = acceptInviteService.acceptInviteCode(validCode);
        //then
        Assertions.assertThat(res).isEqualTo(Response.SUCCESS_CREATE_CODE.getDescription());

        // 실제로 호출 되었는 지 확인
        verify(redisRepository, times(1)).getValue(validCode);
        verify(redisRepository, times(1)).removeValue(validCode);
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void CODE_NOT_FOUND() {
        //when
        when(redisRepository.getValue(validCode)).thenReturn("");
        //then
        assertThatThrownBy(() -> acceptInviteService.acceptInviteCode(validCode))
                .isInstanceOf(InviteException.class)
                .satisfies(ex -> {
                    InviteException inviteException = (InviteException) ex;
                    assertThat(inviteException.getErrorMessage()).isEqualTo(ErrCode.CODE_NOT_FOUND.getDescription());
                });
    }

    @Test
    public void UNABLE_REMOVE_CODE() {
        //when
        when(redisRepository.getValue(validCode)).thenReturn(memberName);
        //then
        assertThatThrownBy(() -> acceptInviteService.acceptInviteCode(validCode))
                .isInstanceOf(InviteException.class)
                .satisfies(ex -> {
                    InviteException inviteException = (InviteException) ex;
                    assertThat(inviteException.getErrorMessage()).isEqualTo(ErrCode.INTERNAL_SERVER_ERROR.getDescription());
                });
    }
}