package com.spring.basic.chap6.exception.custom;

import com.spring.basic.chap6.exception.dto.ErrorCode;
import lombok.Getter;

// 사용자 정의 예외클래스 - 개발자가 원하는 스펙에 맞는 에러 클래스
@Getter
public class MemberException extends RuntimeException {

    private int status; // 에러 상태코드
    private String errorName; // 에러의 이름

    public MemberException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorName = errorCode.toString();
        this.status = errorCode.getStatusCode();
    }
}
