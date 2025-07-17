package com.spring.basic.score.api;

import com.spring.basic.score.dto.response.ScoreListResponse;
import com.spring.basic.score.entity.Score;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/scores")
@Slf4j // 로그 라이브러리
public class ScoreApiController {

    // 저장소 역할을 할 해시맵
    private Map<Long, Score> scoreStore = new HashMap<>();

    private Long nextId = 1L;

    public ScoreApiController() {
        Score s1 = new Score(nextId++, "김말복", 100, 88, 75);
        Score s2 = new Score(nextId++, "박수포자", 55, 95, 15);
        Score s3 = new Score(nextId++, "김마이클", 99, 100, 90);
        Score s4 = new Score(nextId++, "세종대왕", 100, 0, 90);

        scoreStore.put(s1.getId(), s1);
        scoreStore.put(s2.getId(), s2);
        scoreStore.put(s3.getId(), s3);
        scoreStore.put(s4.getId(), s4);
    }

    // 성적 정보 전체 조회
    @GetMapping
    public ResponseEntity<?> scoreList(
            @RequestParam(defaultValue = "id") String sort
    ) {

        log.info("/api/v1/scores GET !");
        log.debug("param: sort - {}", sort);

        // 클라이언트에게 성적정보 목록을 JSON 반환
        List<Score> scores = new ArrayList<>(scoreStore.values());

        // 깔끔하게 응답데이터들을 모아둔 DTO로 변환
        // 석차를 구하는 로직
        calculateListRank(scores);

        List<ScoreListResponse> responses = scores.stream()
                .map(ScoreListResponse::from)
                .collect(Collectors.toList());

        // 정렬 처리
        responses.sort(getScoreComparator(sort));

        return ResponseEntity.ok().body(responses);
    }


    private Comparator<ScoreListResponse> getScoreComparator(String sort) {
        Comparator<ScoreListResponse> comparator = null;
        switch (sort) {
            case "id" -> comparator = Comparator.comparing(ScoreListResponse::getId);
            case "name" -> comparator = Comparator.comparing(ScoreListResponse::getMaskingName);
            case "average" -> comparator = Comparator.comparing(ScoreListResponse::getAvg).reversed();
        }
        return comparator;
    }

    // 원본 리스트에서 석차를 구해서 세팅
    private void calculateListRank(List<Score> scores) {
        // 총점 내림차로 정렬
        scores.sort(Comparator.comparing(Score::getTotal).reversed());
        for (int i = 0; i < scores.size(); i++) {
            scores.get(i).setRank(i + 1);
        }
    }
}
