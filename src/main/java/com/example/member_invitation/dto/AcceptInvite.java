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
    }

    public static AcceptInvite.Response toResponseDto(final String msg) {
        return new AcceptInvite.Response(msg);
    }
}
