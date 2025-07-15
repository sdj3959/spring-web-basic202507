package com.spring.basic.chap3_2.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    private Long id;
    private Long userId;
    private String message;
    private int rating;

}
