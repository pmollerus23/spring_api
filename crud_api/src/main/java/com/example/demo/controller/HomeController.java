package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
    	//model.getAttribute("message");
        //model.addAttribute("message", "You're a fag!");
        //model.addAttribute("year", 2024);
        return "index";  // Return the name of the Thymeleaf template
    }
}
