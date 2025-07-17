package com.spring.basic.score.routes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// JSP 페이지 라우팅을 위한 컨트롤러
@Controller
public class ScoreRouteController {

    // 목록페이지 라우팅
    @GetMapping("/score/page")
    public String scorePage() {
        return "score/score-page";
    }

    // 상세페이지 라우팅
    @GetMapping("/score/{id}")
    public String detailPage(@PathVariable Long id, Model model) {

        // JSP에게 특정 데이터()를 전송
        model.addAttribute("stuId", id);

        return "score/score-detail";
    }
}
