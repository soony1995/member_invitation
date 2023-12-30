package com.example.member_invitation.exception;

import com.example.member_invitation.type.ErrCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
public class InviteException extends RuntimeException {
    private final ErrCode errorCode;
    private final String errorMessage;
    private final HttpStatus statusCode;

    public InviteException(ErrCode errcode) {
        this.errorCode = errcode;
        this.errorMessage = errcode.getDescription();
        this.statusCode = errcode.getHttpStatus();
    }
}
