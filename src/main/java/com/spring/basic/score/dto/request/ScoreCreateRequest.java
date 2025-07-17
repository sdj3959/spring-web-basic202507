package com.spring.basic.score.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

// 클라이언트가 성적 정보를 등록할 때
// 등록에 필요한 데이터를 받기 위한 객체
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCreateRequest {

    // 공백문자, null 둘다 허용하지 않음
    @NotEmpty(message = "이름은 필수값입니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글로만 작성하세요!")
    private String studentName;

    @Min(value = 0, message = "국어 점수는 0점 이상이어야합니다.")
    @Max(value = 100, message = "국어 점수는 100점 이하여야합니다.")
    @NotNull(message = "국어점수는 필수값입니다.")
    private Integer korean;

    @Min(value = 0, message = "영어 점수는 0점 이상이어야합니다.")
    @Max(value = 100, message = "영어 점수는 100점 이하여야합니다.")
    @NotNull(message = "영어점수는 필수값입니다.")
    private Integer english;

    @Min(value = 0, message = "수학 점수는 0점 이상이어야합니다.")
    @Max(value = 100, message = "수학 점수는 100점 이하여야합니다.")
    @NotNull(message = "수학점수는 필수값입니다.")
    private Integer math;
}
