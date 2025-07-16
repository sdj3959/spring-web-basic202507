package com.spring.basic.score.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

// 성적 수정 요청 시 클라이언트가 보낼 데이터를 담는 객체
@Getter @Setter
public class ScoreUpdateRequestDTO {

    // 수정 시에는 학번과 이름이 필요 없을 수 있지만,
    // 서비스 계층에서 필요할 수 있으므로 포함.
    // 여기서는 간단하게 점수만 수정한다고 가정.
    private long id;
    private String name;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하이어야 합니다.")
    private int kor;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하이어야 합니다.")
    private int eng;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하이어야 합니다.")
    private int math;
}