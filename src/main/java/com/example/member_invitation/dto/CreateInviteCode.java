package com.example.member_invitation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class CreateInviteCode {
    public static class Request{
        @NotEmpty
        String inviteMemberName;
        @NotEmpty
        String inviteMemberPhoneNumber;
        @Email
        String inviteMemberEmail;
        @NotEmpty
        Long inviteGroupId;
    }

    public static class Response{
        Long inviteGroupId;
        Long inviteMemberName;
        String inviteCode;
    }
}
