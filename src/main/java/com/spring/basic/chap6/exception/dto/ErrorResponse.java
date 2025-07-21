package com.spring.basic.chap6.exception.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 에러 응답시 사용할 형식있는 객체
public class ErrorResponse {

    private LocalDateTime timestamp; // 에러가 발생한 시간
    private int status; // 에러 상태코드
    private String error; // 에러의 이름
    private String message; // 에러의 원인 메세지
    private String path; // 에러가 발생한 URL 정보
}
