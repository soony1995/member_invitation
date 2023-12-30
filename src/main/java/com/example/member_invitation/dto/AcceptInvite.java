package com.example.member_invitation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AcceptInvite {
    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Response {
        String msg;

        public static Response from(final String msg) {
            return AcceptInvite.Response.builder()
                    .msg(msg)
                    .build();
        }
    }
}
