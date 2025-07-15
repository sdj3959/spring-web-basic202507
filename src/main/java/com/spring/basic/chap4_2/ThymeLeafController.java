package com.spring.basic.chap4_2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chap4-2")
public class ThymeLeafController {

    @GetMapping("/hobby-page")
    public String hoobyPage(Model model) {
        model.addAttribute("username", "또치");
        model.addAttribute("hobbies", List.of("동생 괴롭히기", "폭식하기", "쇠질하기"));
        return "hobby";
    }
}
