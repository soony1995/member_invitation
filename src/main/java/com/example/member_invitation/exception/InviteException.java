package com.example.member_invitation.exception;

import com.example.member_invitation.type.ErrCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteException extends RuntimeException{
    private ErrCode errorCode;
    private String errorMessage;
    private HttpStatus statusCode;

    public InviteException(ErrCode errorCode){
        this.errorCode =errorCode;
        this.statusCode = errorCode.getHttpStatus();
        this.errorMessage= errorCode.getDescription();
    }
}
