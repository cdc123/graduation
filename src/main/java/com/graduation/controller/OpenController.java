package com.graduation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenController {
    @RequestMapping("/")
    public String open(){
        return "redirect:/html/homepage.html";
    }
}
