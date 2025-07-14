package com.spring.basic.chap2_5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v2-5")
public class ResponseController2_5 {

    // 페이지 라우팅 - 특정 뷰 (JSPm thymeleaf)
    @GetMapping("/book-page")
    public String bookPage() {
        return "/book-page";
    }
}
