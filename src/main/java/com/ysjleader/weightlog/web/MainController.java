package com.ysjleader.weightlog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    public String mainPage() {
        return "main";
    }
}
