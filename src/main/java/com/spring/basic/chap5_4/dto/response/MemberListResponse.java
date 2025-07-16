package com.spring.basic.chap5_4.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.basic.chap3_2.entity.Member;
import lombok.*;

import java.time.LocalDate;

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

    @JsonProperty("account")
    private String email; // account에 대응

    private String nick; // 가운데 글자를 마스킹 (첫 글자랑 마지막글자 빼고)

    @JsonIgnore
    private String cardNo;

    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate creationTime;

    // 엔터티를 전달받아서 dto로 변환하는 정적 팩토리 메서드
    public static MemberListResponse from(Member member) {
        return MemberListResponse.builder()
                .id(member.getUid())
                .email(member.getAccount())
                .nick(maskingNickName(member.getNickname()))
                .creationTime(LocalDate.now())
                .build();
    }

    public static String maskingNickName(String originNick) {
        return originNick.charAt(0) + "*" + originNick.charAt(originNick.length() - 1);
    }
}
