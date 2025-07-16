package com.spring.basic.score.entity;

import lombok.*;

// 학생 한 명의 성적 정보를 저장하는 객체의 설계도.
// 이 객체는 데이터베이스의 SCORE 테이블과 1:1로 매핑되는 개념.
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {

    private long id; // 학번
    private String name; // 이름
    private int kor, eng, math; // 국영수 점수

    private int total; // 총점
    private double average; // 평균
    private int rank; // 석차 (이 값은 서비스 계층에서 동적으로 계산하여 세팅)

    /**
     * 총점과 평균을 스스로 계산하는 내부 로직.
     * 객체 지향의 원칙에 따라, 자신의 데이터는 스스로 처리하는 것이 좋음.
     */
    public void calculateTotalAndAverage() {
        this.total = this.kor + this.eng + this.math;
        // 소수점 둘째 자리까지 반올림하여 평균 계산
        this.average = Math.round((this.total / 3.0) * 100) / 100.0;
    }
}