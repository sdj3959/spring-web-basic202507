package com.spring.basic.score.dto.response;

import com.spring.basic.score.entity.Score;
import lombok.*;

// 상세 페이지에서 렌더링할 JSON 데이터를 구성
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDetailResponse {

    private Long id;
    private String fullName;
    private int kor, eng, math;
    private int total;
    private double average;
    private int rank;
    private int totalCount; // 총 학생수

    // 변환 메서드
    public static ScoreDetailResponse of(Score score, int totalCount) {
        return ScoreDetailResponse.builder()
                .id(score.getId())
                .fullName(score.getName())
                .kor(score.getKor())
                .eng(score.getEng())
                .math(score.getMath())
                .total(score.getTotal())
                .average(score.getAverage())
                .rank(score.getRank())
                .totalCount(totalCount)
                .build();
    }
}
