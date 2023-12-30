package com.example.member_invitation.domain;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor // jpa를 이용할 떄 reflexion에서 사용하기 위해 필요하다.
@Builder
public class Invitation {
    String code;

    public String createCode(){
        return UUID.randomUUID().toString();
    }
}
