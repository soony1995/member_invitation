package com.example.member_invitation.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invitation {
    String code;

    public String createCode(){
        return UUID.randomUUID().toString();
    }
}
