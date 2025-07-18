package com.spring.basic.chap5_5.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 게시글 피드 한 개의 내용을 저장
public class Feed {
    private Long feedId; // 피드 식별번호
    private String content; // 피드 내용
    private String writer; // 작성자명
    private LocalDateTime createdAt; // 작성시간
    private int viewCount; // 조회수
}
