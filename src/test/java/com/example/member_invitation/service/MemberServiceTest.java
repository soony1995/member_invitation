package com.example.member_invitation.service;

import com.example.member_invitation.domain.Member;
import com.example.member_invitation.exception.MemberException;
import com.example.member_invitation.repository.MemberRepository;
import com.example.member_invitation.type.ErrCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @Test
    public void GOOD() {
        // given
        long id = 1L;
        Member member = new Member();
        when(memberRepository.findById(id)).thenReturn(Optional.of(member));
        // when
        Optional<Member> res = memberService.getMember(id);
        // then
        assertThat(res).isPresent().contains(member);
    }

    @Test
    void getMember_WithInvalidId_ThrowsException() {
        // given
        Long invalidId = 0L;

        // when & then
        assertThatThrownBy(() -> memberService.getMember(invalidId ))
                .isInstanceOf(MemberException.class)
                .satisfies(ex -> {
                    MemberException memberException = (MemberException) ex;
                    assertThat(memberException.getErrorMessage()).isEqualTo(ErrCode.BAD_REQUEST.getDescription());
                });
    }

    @Test
    void getMember_WithNonExistingId_ThrowsException() {
        // given
        Long nonExistingId = 99L;
        when(memberRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> memberService.getMember(nonExistingId))
                .isInstanceOf(MemberException.class)
                .satisfies(ex -> {
                    MemberException memberException = (MemberException) ex;
                    assertThat(memberException.getErrorMessage()).isEqualTo(ErrCode.ACCOUNT_NOT_FOUND.getDescription());
                });
    }

}