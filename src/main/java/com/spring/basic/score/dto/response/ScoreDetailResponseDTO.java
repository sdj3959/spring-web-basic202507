package com.spring.basic.score.dto.response;

import lombok.*;

// 상세 화면에 필요한 모든 데이터를 담아서 보내기 위한 객체.
@Getter @ToString @EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ScoreDetailResponseDTO {
    private long id;
    private String fullName; // 전체 이름
    private int kor, eng, math;
    private int total;
    private double average;
    private int rank;
    private int totalCount; // 전체 학생 수
}