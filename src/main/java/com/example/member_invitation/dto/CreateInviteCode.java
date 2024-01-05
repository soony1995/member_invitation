package com.example.member_invitation.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

public class CreateInviteCode {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String inviteMemberName;
        private String inviteMemberPhoneNumber;
        @Email
        private String inviteMemberEmail;
        private Long inviteGroupId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        String inviteCode;
        public static Response from(final String code) {
            return Response.builder()
                    .inviteCode(code)
                    .build();
        }
    }
}
