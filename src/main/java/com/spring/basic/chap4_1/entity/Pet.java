package com.spring.basic.chap4_1.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {
    private Long id;
    private String name;
    private String kind;
    private boolean injection;
    private int age;
}
