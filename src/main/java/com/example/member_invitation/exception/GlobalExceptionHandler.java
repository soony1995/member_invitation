package com.example.member_invitation.exception;

import com.example.member_invitation.dto.ErrorResponse;
import com.example.member_invitation.type.ErrCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InviteException.class)
    public ResponseEntity<ErrorResponse> handleAccountException(InviteException e) {
        return buildResponseEntity(e.getMessage(), e.getErrorCode(), e.getStatusCode());
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> handleMemberException(MemberException e) {
        return buildResponseEntity(e.getMessage(), e.getErrorCode(), e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return buildResponseEntity(ErrCode.INTERNAL_SERVER_ERROR.getDescription(), ErrCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(String logMessage, ErrCode errorCode, HttpStatus status) {
        log.error(logMessage);
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        return ResponseEntity.status(status).body(errorResponse);
    }
}
