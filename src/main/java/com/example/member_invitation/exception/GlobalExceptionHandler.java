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
        log.error("{} is occurred", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode());
        // 여기서 적절한 HTTP 상태 코드를 설정합니다. 예를 들어, HttpStatus.NOT_FOUND
        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Exception is occurred.", e);

        ErrorResponse errorResponse = new ErrorResponse(ErrCode.INTERNAL_SERVER_ERROR);
        // 일반적인 예외는 내부 서버 오류로 처리합니다.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
