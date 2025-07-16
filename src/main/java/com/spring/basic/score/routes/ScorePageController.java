package com.spring.basic.score.routes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/score")
public class ScorePageController {

    // 성적 목록 및 등록 페이지
    // URL: /score/list
    @GetMapping("/list")
    public String showListPage(Model model) {
        model.addAttribute("title", "성적 관리");
        return "score/score-page";
    }

    // 성적 상세 정보 페이지
    // URL: /score/detail/{id}
    @GetMapping("/detail/{id}")
    public String showDetailPage(@PathVariable long id, Model model) {
        model.addAttribute("studentId", id);
        return "score/score-detail";
    }
}