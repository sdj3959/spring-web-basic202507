package com.spring.basic.chap5_5.dto.request;

import com.spring.basic.chap5_5.entity.Feed;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 클라이언트가 피드를 작성할 때 작성자, 내용만 전달하더라
// 이것만 컴팩트하게 받아줄 객체 + 입력값 검증
public class FeedCreateRequest {

    @NotEmpty(message = "피드 내용은 필수입니다.")
    private String content;

    @NotEmpty(message = "피드 작성자 이름은 필수입니다.")
    private String writer;

    // dto를 엔터티로 변경하는 유틸 메서드
    public static Feed toEntity(FeedCreateRequest dto) {
        return Feed.builder()
                .createdAt(LocalDateTime.now())
                .writer(dto.getWriter())
                .content(dto.getContent())
                .build();
    }
}