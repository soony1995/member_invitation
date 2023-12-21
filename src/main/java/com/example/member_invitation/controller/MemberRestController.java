package com.example.member_invitation.controller;
import com.example.member_invitation.domain.Member;
import com.example.member_invitation.dto.GetMember;
import com.example.member_invitation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // 의존성 주입에 필요한 인자들을 받아와준다.
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public Member getMember (
            @PathVariable Long id
    ){
        return memberService.getMember(id);
    }
}
