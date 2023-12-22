package com.example.member_invitation.controller;

import com.example.member_invitation.dto.AcceptInvite;
import com.example.member_invitation.dto.CreateInviteCode;
import com.example.member_invitation.service.AcceptInviteCode;
import com.example.member_invitation.service.InvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InvitationRestController {
    public final InvitationService invitationService;
    public final AcceptInviteCode  acceptInviteCode;

    @PostMapping("/invite")
    public CreateInviteCode.Response createInviteCode(
            @Valid @RequestBody final CreateInviteCode.Request dto
    ) {
        String code = invitationService.createInviteCode(dto);
        return CreateInviteCode.toResponseDto(code);
    }

    @GetMapping("/accept/{code}")
    public AcceptInvite.Response acceptInviteCode(@PathVariable String code) {
        String msg = acceptInviteCode.acceptInviteCode(code);
        return AcceptInvite.toResponseDto(msg);
    }
}

