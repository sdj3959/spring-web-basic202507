package com.spring.basic.chap5_4.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 클라이언트에게 멤버 목록을 보내줄 때 사용할 응답 DTO
public class MemberListResponse {
    private String id;
    private String email; // account에 대응
    private String nick; // 가운데 글자를 마스킹 (첫 글자랑 마지막글자 빼고)
}
