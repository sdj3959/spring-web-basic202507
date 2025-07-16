package com.spring.basic.chap5_3.dto.request;

import lombok.*;

// 회원 가입 전용 객체
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCreateDto {

    private String userAcc;
    private String pw;
    private String nick;
}
