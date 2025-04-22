package org.example.employeethymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/homePage")
    public String homePage() {
        return "home";
    }

}
