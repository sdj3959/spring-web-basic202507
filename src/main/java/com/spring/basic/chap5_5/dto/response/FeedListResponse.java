package com.spring.basic.chap5_5.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.basic.chap5_5.entity.Feed;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 클라이언트에게 피드 목록을 전달할 때 JSON 데이터를 정제하는 역할
public class FeedListResponse {

    @JsonProperty("feed_id")
    private Long id;
    private String author;
    @JsonFormat(pattern = "yyyy년MM월dd일")
    private LocalDateTime regDate;

    // 엔터티를 DTO로 변환하는 유틸 메서드
    public static FeedListResponse from(Feed feed) {
        return FeedListResponse.builder()
                .id(feed.getFeedId())
                .author(feed.getWriter())
                .regDate(feed.getCreatedAt())
                .build();
    }
}