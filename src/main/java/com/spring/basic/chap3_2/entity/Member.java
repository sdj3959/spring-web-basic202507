package com.spring.basic.chap3_2.entity;

import lombok.*;

import java.util.UUID;

@Getter @Setter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Builder.Default
    private String uid = UUID.randomUUID().toString(); // 회원 식별번호

    private String account;
    private String password;
    private String nickname;

    // DTO를 Entity로 변환하는 생성자
//    public Member(MemberCreateDto dto) {
//        this.uid = UUID.randomUUID().toString();
//        this.account = dto.getUserAcc();
//        this.password = dto.getPw();
//        this.nickname = dto.getNick();
//    }

    /*private Member(Builder builder) {
        this.uid = UUID.randomUUID().toString();
        this.account = builder.account;
        this.password = builder.password;
        this.nickname = builder.nickname;
    }

    // 빌더 패턴 구현 - 생성자를 대체하는 것
    public static class Builder {
        // 원본 클래스랑 완벽하게 동일한 필드를 구성
        private String uid; // 회원 식별번호
        private String account;
        private String password;
        private String nickname;

        public Builder() {}

        // 필드를 초기화하는 setter를 자기 필드명과 동일하게 생성
        public Builder account(String account) {
            // 자기 자신 객체를 리턴
            this.account = account;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        // 최종연산에서는 원본 객체를 리턴
        public Member build() {
            return new Member(this);
        }
    }*/
}
