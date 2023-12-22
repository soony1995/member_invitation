package com.example.member_invitation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {
    DEACTIVATE("그룹 초대 등을 통한 임시 가입 상태"),
    ACTIVATE("계정 활성화 상태"),
    ;

    private final String description;
}
