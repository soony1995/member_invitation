package com.example.member_invitation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ErrCode {
    CODE_NOT_FOUND(HttpStatus.NOT_FOUND,"코드를 찾을 수 없습니다."),
    ACCOUNT_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST,"이미 있는 계정입니다."),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND,"사용자를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"서버 내부에 문제가 생겼습니다.");

    private final HttpStatus httpStatus;
    private final String description;
}
