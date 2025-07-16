package com.spring.basic.score.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// 목록 화면에 필요한 특정 데이터(마스킹된 이름 등)만 담아서 보내기 위한 객체.
@Getter @ToString @EqualsAndHashCode
@AllArgsConstructor
public class ScoreListResponseDTO {
    private long id;
    private String maskingName; // 마스킹 처리된 이름 (예: 김*수)
    private int sum; // 총점
    private double avg; // 평균
    private int rank; // 석차
}