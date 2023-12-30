package com.example.member_invitation.exception;

import com.example.member_invitation.type.ErrCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class MemberException extends RuntimeException {
    private final ErrCode errorCode;
    private final String errorMessage;
    private final HttpStatus statusCode;

    public MemberException(ErrCode errcode) {
        this.errorCode = errcode;
        this.errorMessage = errcode.getDescription();
        this.statusCode = errcode.getHttpStatus();
    }
}