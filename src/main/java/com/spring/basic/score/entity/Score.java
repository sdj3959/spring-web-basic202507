package com.spring.basic.score.entity;

import lombok.*;

// 학생 한명의 성적정보를 저장
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Long id; // 학번
    private String name; // 이름;
    private int kor, eng, math; // 국영수 점수
    private int total; // 총점
    private double average; // 평균
    private int rank;


    public Score(Long id, String name, int kor, int eng, int math) {
        this.id = id;
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        calcTotalAndAverage();
    }

    // 총점 평균을 구하는 메서드
    private void calcTotalAndAverage() {
        this.total = this.kor + this.eng + this.math;
        this.average = this.total / 3.0;
    }
}
