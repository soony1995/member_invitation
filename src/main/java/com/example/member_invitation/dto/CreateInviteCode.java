package com.example.member_invitation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

public class CreateInviteCode {
    @Getter
    @Setter
    public static class Request {
        private String inviteMemberName;
        private String inviteMemberPhoneNumber;
        @Email
        private String inviteMemberEmail;
        private Long inviteGroupId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        String inviteCode;
    }

    public static CreateInviteCode.Response toResponseDto(final String code) {
        return new CreateInviteCode.Response(code);
    }
}
