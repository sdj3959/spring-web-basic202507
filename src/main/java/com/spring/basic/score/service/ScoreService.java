package com.spring.basic.score.service;

import com.spring.basic.score.dto.request.ScoreRequestDTO;
import com.spring.basic.score.dto.request.ScoreUpdateRequestDTO;
import com.spring.basic.score.dto.response.ScoreDetailResponseDTO;
import com.spring.basic.score.dto.response.ScoreListResponseDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository repository;

    public List<ScoreListResponseDTO> getList(String sort) {
        List<Score> scoreList = repository.findAll();
        calculateRank(scoreList); // 석차 계산 먼저 수행

        Comparator<Score> comparator = getComparator(sort);

        return scoreList.stream()
                .sorted(comparator)
                .map(this::convertToScoreListDTO)
                .collect(Collectors.toList());
    }

    public void register(ScoreRequestDTO dto) {
        Score newScore = new Score();
        newScore.setName(dto.getStudentName());
        newScore.setKor(dto.getKorean());
        newScore.setEng(dto.getEnglish());
        newScore.setMath(dto.getMath());
        repository.save(newScore);
    }

    public ScoreDetailResponseDTO retrieve(long id) {
        Score score = repository.findById(id);
        if (score == null) return null;

        List<Score> scoreList = repository.findAll();
        calculateRank(scoreList);

        return convertToScoreDetailDTO(score, scoreList.size());
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public void update(long id, ScoreUpdateRequestDTO dto) {
        Score score = repository.findById(id);
        if (score == null) {
            throw new RuntimeException("학생 정보를 찾을 수 없습니다.");
        }
        score.setKor(dto.getKor());
        score.setEng(dto.getEng());
        score.setMath(dto.getMath());
        score.calculateTotalAndAverage();
    }

    // == private 헬퍼 메서드들 == //

    private Comparator<Score> getComparator(String sort) {
        switch (sort) {
            case "name": return Comparator.comparing(Score::getName);
            case "average": return Comparator.comparingDouble(Score::getAverage).reversed();
            default: return Comparator.comparingLong(Score::getId);
        }
    }

    /**
     * 석차 계산 로직 (수정됨)
     */
    private void calculateRank(List<Score> scoreList) {
        // 총점을 기준으로 내림차순 정렬된 리스트를 만듦
        List<Score> sortedByTotal = scoreList.stream()
                .sorted(Comparator.comparingInt(Score::getTotal).reversed())
                .collect(Collectors.toList());

        // 원본 리스트의 각 Score 객체에 대해 올바른 석차를 부여
        for (Score score : scoreList) {
            // 정렬된 리스트에서 현재 객체의 위치(인덱스)를 찾아서 +1 하면 등수
            int rank = sortedByTotal.indexOf(score) + 1;
            score.setRank(rank); // setRank()로 석차를 설정
        }
    }

    private ScoreListResponseDTO convertToScoreListDTO(Score s) {
        String maskingName = s.getName().length() > 1 ? s.getName().charAt(0) + "**" : s.getName();
        return new ScoreListResponseDTO(s.getId(), maskingName, s.getTotal(), s.getAverage(), s.getRank());
    }

    private ScoreDetailResponseDTO convertToScoreDetailDTO(Score s, int totalCount) {
        return ScoreDetailResponseDTO.builder()
                .id(s.getId())
                .fullName(s.getName())
                .kor(s.getKor())
                .eng(s.getEng())
                .math(s.getMath())
                .total(s.getTotal())
                .average(s.getAverage())
                .rank(s.getRank())
                .totalCount(totalCount)
                .build();
    }
}