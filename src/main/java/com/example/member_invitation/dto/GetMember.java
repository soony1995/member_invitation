package com.example.member_invitation.dto;

import lombok.Getter;
import lombok.Setter;

public class GetMember {
    @Getter
    @Setter
    public static class Response{
        private long id;
        private long createdAt;
        private long lastModifiedAt;
        private String name;
        private String phoneNumber;
        private String email;
        private String status;
    }
}
