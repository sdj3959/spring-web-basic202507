package com.spring.basic.chap2_2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller // JSP같은 뷰를 핸들링하는 컨트롤러
@RestController // JSON을 리턴하는 컨트롤러
@RequestMapping("chap2-2")
public class BasicController {

    /*
        GET     : 조회
        POST    : 생성
        PUT     : 수정
        DELETE  : 삭제
     */

    // 핸들러 메서드 (각 요청을 전담처리)
//    @RequestMapping(value = "chap2-2/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
//    @ResponseBody // JSON 리턴
    public String hello() {
        return "hello spring";
    }

    @PostMapping("/hobby")
    public List<String> greet() {
        return List.of("수영", "축구", "낮잠", "시비걸기");
    }
}
