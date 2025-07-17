package com.spring.basic.score.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// JSP 페이지 라우팅을 위한 컨트롤러
@Controller
public class ScoreRouteController {

    @GetMapping("/score/page")
    public String scorePage() {
        return "score/score-page";
    }
}
