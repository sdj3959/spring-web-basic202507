package com.spring.basic.chap2_5.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping("/api/v2-5")
public class ResponseController2_5 {

    // 페이지 라우팅 - 특정 뷰 (JSP, thymeleaf)를 포워딩 해주는 것
    @GetMapping("/book-page")
    public String bookPage() {
        return "book-page";
    }

    // 클라이언트에게 데이터 응답
    // html 응답
    @GetMapping("/show/html")
    @ResponseBody // 순수 데이터를 클라이언트에게 전달
    public String html() {
        return """
                <html>
                <body>
                    <h1>HTML 응답하기</h1>
                </body>
                </html>
                """;
    }

    // 순수 텍스트 응답하기
    @GetMapping(value="/show/text", produces = "text/plain")
    @ResponseBody // 2가지 : JSON이거나 HTML
    public String text() {
        return "하이 난 문자야~~";
    }

    // 순수 텍스트 응답하기2
    @GetMapping(value="/show/text2", produces = "application/json")
    @ResponseBody
    public Map<String, Object> text2() {
        return Map.of(
                "message", "하이 난 문자야2~~2"
        );
    }

    // JSON배열 응답 - 자바 배열이나 리스트, 셋
    @GetMapping("/json/array")
    @ResponseBody
    public List<String> hobbies() {
        return List.of("테니스", "수학문제풀기", "탁구");
    }

    // JSON 객체 응답 - 자바의 클래스의 인스턴스(객체) or Map
    @GetMapping("/json/object")
    @ResponseBody
    public Map<String, Object> myPet() {
        return Map.of(
                "name", "야옹이",
                "age", 3,
                "kind", "코리안숏헤어"
        );
    }

    @GetMapping("/json/object2")
    @ResponseBody
    public Pet myPet2() {
        return new Pet("냥냥이", 5, "페르시안", true);
    }

    @GetMapping("/json/object3")
    @ResponseBody
    public List<Pet> myPet3() {
        return List.of(
                new Pet("냥냥이", 5, "페르시안", true),
                new Pet("냥냥이2", 6, "페르시안2", false),
                new Pet("냥냥이3", 7, "페르시안3", true),
                new Pet("냥냥이4", 8, "페르시안4", false)
        );
    }

    @ToString @Getter
    @AllArgsConstructor
    public static class Pet {
        private String name;
        private int age;
        private String kind;
        private boolean injection;
    }

}
