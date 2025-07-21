package com.spring.basic.chap6.exception;

import com.spring.basic.chap6.exception.custom.MemberException;
import com.spring.basic.chap6.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

// API에서 발생하는 수많은 예외상황들을 도맡아서 처리하는 클래스
@Slf4j
@ControllerAdvice  // 컨트롤러 대신 예외처리를 하겠다
public class GlobalExceptionHandler {

    // 예외처리 메서드
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> handleClientException(
            MemberException e
            , HttpServletRequest request
    ) {
        // 로깅 처리
        log.warn("exception occurred! caused by: {}", e.getMessage());

        // 구체적인 에러 정보 객체를 생성
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(e.getStatus())
                .error(e.getErrorName())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        // 에러 응답 처리
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    // 입력값 검증 에러 통합 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> hanldeBindingError(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .status(e.getStatusCode().value())
                .error(e.getStatusCode().toString())
                .message(e.getBindingResult().getFieldError().getDefaultMessage())
                .build();

        return ResponseEntity.status(e.getStatusCode().value()).body(errorResponse);
    }
}
