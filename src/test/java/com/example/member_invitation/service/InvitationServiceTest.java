package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.dto.CreateInviteCode;
import com.example.member_invitation.exception.InviteException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.repository.RedisRepository;
import com.example.member_invitation.type.ErrCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvitationServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RedisRepository redisRepository;

    @InjectMocks
    private InvitationService invitationService;

    private CreateInviteCode.Request requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new CreateInviteCode.Request().builder()
                .inviteMemberPhoneNumber("fd1234")
                .inviteGroupId(1234L)
                .inviteMemberName("sooncheol1")
                .inviteMemberEmail("dfas@naver.com")
                .build();
    }

    @Test
    void 정상케이스() {
        // given
        when(memberRepository.findByName(requestDto.getInviteMemberName())).thenReturn(Optional.empty());

        // when
        String code = invitationService.createInviteCode(requestDto);

        // then
        assertThat(code).isNotEmpty();

        verify(redisRepository, times(1)).setValue(eq(code), eq(requestDto.getInviteMemberName()), any());
        verify(memberRepository, times(1)).save(any());
    }

    @Test
    void 이미존재하는회원() {
        // given
        when(memberRepository.findByName(requestDto.getInviteMemberName())).thenReturn(Optional.of(mock(Member.class)));
        // when

        assertThatThrownBy(() -> invitationService.createInviteCode(requestDto))
                .isInstanceOf(InviteException.class)
                .satisfies(ex -> {
                    InviteException inviteException = (InviteException) ex;
                    assertThat(inviteException.getErrorMessage()).isEqualTo(ErrCode.ACCOUNT_ALREADY_REGISTERED.getDescription());
                });
    }
}