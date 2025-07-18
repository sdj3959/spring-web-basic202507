package com.spring.basic.chap5_5.controller;

import com.spring.basic.chap5_5.entity.Feed;
import com.spring.basic.chap5_5.repository.FeedRepository;
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
@RequiredArgsConstructor // final 필드만 파라미터로 받는 생성자
public class FeedController {

    private final FeedRepository feedRepository;
    private int a;

//    @Autowired
//    public FeedController(FeedRepository feedRepository) {
//        this.feedRepository = feedRepository;
//    }

    // 피드 전체 조회 요청
    @GetMapping
    public ResponseEntity<List<Feed>> feedList() {
        // 데이터창고에서 피드 목록을 가져와서 클라이언트에게 응답
        // 응답할 때 클라이언트에게 필요한 데이터만 정제해서 줘야 함.
        List<Feed> feeds = feedRepository.getFeeds();

        return ResponseEntity.ok().body(feeds);
    }

    // 피드 등록 요청
    @PostMapping
    public ResponseEntity<?> createFeed(@RequestBody Feed feed) {

        // repository에게 저장을 위임
        feedRepository.save(feed);

        return ResponseEntity.ok("피드 저장 성공!");
    }

    // 피드 삭제 요청 /api/v5-5/feeds/{id} : DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeed(@PathVariable Long id) {

        // repository에게 삭제를 위임
        feedRepository.delete(id);
        return ResponseEntity.ok(id+"번 게시물 삭제완료!");
    }


    // 피드 개별조회 요청 api/v5-5/fees/{id} : GET
    @GetMapping("/{id}")
    public ResponseEntity<Feed> getFeedById(@PathVariable Long id) {

        return ResponseEntity.ok().body(feedRepository.getFeedById(id));
    }
}
