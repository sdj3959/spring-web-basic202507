package com.spring.basic.chap5_5.repository;

import com.spring.basic.chap5_5.entity.Feed;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 게시물 피드 데이터를 전담 관리하는 클래스
// 관심사: 데이터를 다루는 것만 관심있음. 요청응답 -> 관심 없음
@Repository
public class FeedRepository {

    private Map<Long, Feed> feedStore = new HashMap<>();
    private Long feedNextId = 1L;

    public FeedRepository() {
        Feed f1 = Feed.builder()
                .feedId(feedNextId++)
                .content("하하호호 재밌다.")
                .writer("호빵맨")
                .createAt(LocalDateTime.of(2025, 3, 14, 13, 15, 0))
                .build();
        Feed f2 = Feed.builder()
                .feedId(feedNextId++)
                .content("산리오 친구들 모여라~~")
                .writer("헬로키티")
                .createAt(LocalDateTime.of(2025, 5, 27, 9, 23, 13))
                .build();

        feedStore.put(f1.getFeedId(), f1);
        feedStore.put(f2.getFeedId(), f2);
    }

    // 데이터 전체조회를 하는 로직
    public List<Feed> getFeeds() {
        return new ArrayList<>(feedStore.values());
    }

    // 데이터 저장 로직
    public void save(Feed feed) {
        feed.setFeedId(feedNextId++);
        feed.setCreateAt(LocalDateTime.now());

        feedStore.put(feed.getFeedId(), feed);
    }

    // 데이터 삭제 로직
    public void delete(Long id) {
        feedStore.remove(id);
    }

    // 데이터 개별 조회 로직
    public Feed getFeedById(Long id) {
        return feedStore.get(id);
    }
}
