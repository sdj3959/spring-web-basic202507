package com.spring.basic.score.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

// 성적 등록 요청 시 클라이언트(브라우저)가 보낼 데이터를 안전하게 담는 객체.
// 입력값 검증(Validation) 로직을 포함.
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRequestDTO {

    @NotBlank(message = "이름은 필수값입니다.")
    private String studentName;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하이어야 합니다.")
    private int korean;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하이어야 합니다.")
    private int english;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하이어야 합니다.")
    private int math;
}