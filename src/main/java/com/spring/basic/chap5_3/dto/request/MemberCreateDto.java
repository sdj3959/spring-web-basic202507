package com.spring.basic.chap5_3.dto.request;

import com.spring.basic.chap3_2.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 회원 가입 전용 객체
public class MemberCreateDto {

    @Email(message = "계정명은 이메일 형식을 지켜주세요.")
    @NotBlank(message = "계정명은 필수입니다.")
    private String userAcc;

    @NotBlank(message = "비밀번호는 필수입니다.")
//    @Size(min = 8, message = "8자리 이상으로 작성하세요.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).{8,}$", message = "비밀번호는 특수문자와 숫자 영문을 포함해야 합니다.")
    private String pw;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nick;

    // DTO를 Entity로 변환하는 메서드
    // 정적 팩토리 메서드 패턴
    public static Member from(MemberCreateDto dto) {
        return Member.builder()
                .account(dto.getUserAcc())
                .password(dto.getPw())
                .nickname(dto.getNick())
                .build();
    }
}
