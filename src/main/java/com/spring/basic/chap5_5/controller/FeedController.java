package com.spring.basic.chap5_5.controller;

import com.spring.basic.chap5_5.dto.request.FeedCreateRequest;
import com.spring.basic.chap5_5.dto.response.FeedDetailResponse;
import com.spring.basic.chap5_5.dto.response.FeedListResponse;
import com.spring.basic.chap5_5.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 피드에 관련된 요청과 응답을 처리
// 관심사: 클라이언트 요청을 받고 응답을 전해주는 것
// 실제로 데이터를 관리하는 것 -> 관심 X
@RestController
@RequestMapping("/api/v5-5/feeds")
@Slf4j
@RequiredArgsConstructor // final필드만 파라미터로 받는 생성자
public class FeedController {

    private final FeedService feedService;

    // 피드 전체 조회 요청
    @GetMapping
    public ResponseEntity<?> feedList() {
        // 데이터창고에서 피드목록을 가져와서 클라이언트에게 응답
        // 응답할 때 클라이언트에게 필요한데이터만 정제해서 줘야 함.
        List<FeedListResponse> responses = feedService.listProcess();

        return ResponseEntity.ok().body(responses);
    }

    // 피드 등록 요청
    @PostMapping
    public ResponseEntity<?> createFeed(@RequestBody FeedCreateRequest dto) {

        // service 에게 저장을 위임
        feedService.createProcess(dto);

        return ResponseEntity.ok("피드 저장 성공!");

    }

    // 피드 삭제 요청 /api/v5-5/feeds/{id} : DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeed(@PathVariable Long id) {

        try {
            feedService.removeProcess(id);
            return ResponseEntity.ok("삭제 성공!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    // 피드 개별조회 요청 /api/v5-5/feeds/{id} : GET
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {

        // 피드 Id, 생성시간 제외
        // 작성자명 (writer), 피드내용은 (feed_content), 조회수 (view) 응답
        FeedDetailResponse response = feedService.findOneProcess(id);

        return ResponseEntity.ok(response);
    }
}