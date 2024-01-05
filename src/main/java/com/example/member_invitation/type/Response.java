package com.example.member_invitation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Response {
    SUCCESS_CREATE_CODE("초대 코드 생성이 완료되었습니다.");

    private final String description;
}
