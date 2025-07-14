package com.spring.basic.chap2_3.practice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/practice/api/v1")
public class PracticeController2_3 {

    // 문제 1
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring MVC!";
    }

    // 문제 2
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") String productId) {
        return "Product ID: " + productId;
    }

    // 문제 3
    @GetMapping("/books")
    public String getBooks(String author, String genre) {
        return "Author: " + author + ", Genre: " + genre;
    }

    // 문제 4
    @GetMapping("/search")
    public String search(@RequestParam("query") String query,
                         @RequestParam(value = "page", defaultValue = "1") int page) {
        return "Query: " + query + ", Page: " + page;
    }
}