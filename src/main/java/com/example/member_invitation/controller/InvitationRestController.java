package com.example.member_invitation.controller;

import com.example.member_invitation.dto.CreateInviteCode;
import com.example.member_invitation.service.InvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InvitationRestController {
    public final InvitationService invitationService;

    @PostMapping("/invite/")
    public CreateInviteCode.Response createInviteCode(
            @RequestBody @Valid final CreateInviteCode.Request dto
    ){
//        Invitation resVo = invitationService.createInviteCode(dto);
    }

}
