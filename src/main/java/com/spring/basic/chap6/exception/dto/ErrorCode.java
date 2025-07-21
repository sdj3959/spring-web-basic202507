package com.spring.basic.chap6.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(404, "회원을 찾을 수 없습니다."),
    ACCOUNT_TOO_LONG(400, "계정명은 10글자 이내여야 합니다.")
    ;

    private final int statusCode;
    private final String errorMessage;

}
