package com.spring.basic.score.repository;

import com.spring.basic.score.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.*;

// 데이터 저장소 역할을 하는 클래스. DB와의 상호작용을 담당.
// 지금은 DB 대신 메모리의 Map을 사용 (인메모리 데이터베이스).
@Repository // 스프링 컨테이너에 빈(Bean)으로 등록하여 다른 곳에서 주입받을 수 있게 함.
public class ScoreRepository {

    // Key: 학번(Long), Value: 성적정보(Score)
    // static으로 선언하여 모든 요청에서 단 하나의 DB(Map)를 공유.
    private static final Map<Long, Score> scoreDatabase = new HashMap<>();
    private static long sequence = 1L; // 학번을 자동으로 생성하기 위한 시퀀스.

    // static 초기화 블록: 클래스가 로드될 때 샘플 데이터를 미리 넣어둠.
    static {
        saveSampleData(new Score(0L, "김철수", 88, 77, 99, 0, 0, 0));
        saveSampleData(new Score(0L, "박영희", 100, 50, 80, 0, 0, 0));
        saveSampleData(new Score(0L, "고길동", 55, 66, 77, 0, 0, 0));
        saveSampleData(new Score(0L, "세종대왕", 99, 98, 97, 0, 0, 0));
    }

    // 전체 성적 정보를 리스트 형태로 반환.
    public List<Score> findAll() {
        return new ArrayList<>(scoreDatabase.values());
    }

    // 성적 정보 등록.
    public void save(Score score) {
        score.setId(sequence++); // 새로운 학번을 부여.
        score.calculateTotalAndAverage(); // 총점, 평균 계산.
        scoreDatabase.put(score.getId(), score);
    }

    // 샘플 데이터 저장을 위한 내부 메서드
    private static void saveSampleData(Score score) {
        score.setId(sequence++);
        score.calculateTotalAndAverage();
        scoreDatabase.put(score.getId(), score);
    }

    // 학번으로 성적 정보 조회.
    public Score findById(long id) {
        return scoreDatabase.get(id);
    }

    // 학번으로 성적 정보 삭제.
    public void deleteById(long id) {
        scoreDatabase.remove(id);
    }
}