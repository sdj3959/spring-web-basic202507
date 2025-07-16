package com.spring.basic.score.api;

import com.spring.basic.score.dto.request.ScoreRequestDTO;
import com.spring.basic.score.dto.request.ScoreUpdateRequestDTO;
import com.spring.basic.score.dto.response.ScoreDetailResponseDTO;
import com.spring.basic.score.dto.response.ScoreListResponseDTO;
import com.spring.basic.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JavaScript의 fetch 요청(비동기)을 처리하고, 순수 데이터(JSON)를 응답하는 컨트롤러.
@RestController
@RequestMapping("/api/v1/scores")
@RequiredArgsConstructor
public class ScoreApiController {

    private final ScoreService scoreService;

    // 성적 목록 조회
    // URL: /api/v1/scores?sort=average
    @GetMapping
    public ResponseEntity<List<ScoreListResponseDTO>> getScores(@RequestParam(defaultValue = "id") String sort) {
        List<ScoreListResponseDTO> list = scoreService.getList(sort);
        return ResponseEntity.ok().body(list);
    }

    // 성적 상세 조회
    // URL: /api/v1/scores/3
    @GetMapping("/{id}")
    public ResponseEntity<?> getScore(@PathVariable long id) {
        ScoreDetailResponseDTO dto = scoreService.retrieve(id);
        if (dto == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found 응답
        }
        return ResponseEntity.ok().body(dto);
    }

    // 성적 등록
    // URL: /api/v1/scores (POST 방식)
    @PostMapping
    public ResponseEntity<?> createScore(
            @Validated @RequestBody ScoreRequestDTO requestDTO,
            BindingResult result // @Validated의 검증 결과를 담는 객체
    ) {
        // 입력값 검증(Validation)에 실패한 경우
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                // error.getField() : DTO의 필드명 (korean, english 등)
                // error.getDefaultMessage() : DTO에 작성한 메시지
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            // 400 Bad Request와 함께 에러 메시지 맵을 응답.
            return ResponseEntity.badRequest().body(errorMap);
        }

        scoreService.register(requestDTO);
        // 성공 시 200 OK와 함께 성공 메시지를 응답.
        return ResponseEntity.ok().body("create success!");
    }

    // 성적 삭제
    // URL: /api/v1/scores/3 (DELETE 방식)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable long id) {
        scoreService.delete(id);
        return ResponseEntity.ok().body("delete success!");
    }

    // 성적 정보 수정 (PUT 또는 PATCH 방식)
    // PATCH는 일부만 수정, PUT은 전체를 교체하는 의미. 여기서는 PUT을 사용.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScore(
            @PathVariable long id, // URL 경로에서 학번을 읽음
            @Validated @RequestBody ScoreUpdateRequestDTO requestDTO, // 요청 본문의 JSON을 DTO로 변환 및 검증
            BindingResult result
    ) {
        // 입력값 검증(Validation)에 실패한 경우
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                // JSP의 에러 메시지 span의 id와 맞추기 위해 필드명 가공
                String fieldName = error.getField(); // "kor", "eng" 등
                errorMap.put(fieldName, error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
        }

        try {
            // 서비스 계층에 수정 로직 위임
            scoreService.update(id, requestDTO);
            return ResponseEntity.ok().build(); // 성공 시 200 OK
        } catch (Exception e) {
            // e.getMessage() 등을 로그로 남기는 것이 좋음
            return ResponseEntity.notFound().build(); // 존재하지 않는 ID 등으로 인한 예외 발생 시 404
        }
    }

}