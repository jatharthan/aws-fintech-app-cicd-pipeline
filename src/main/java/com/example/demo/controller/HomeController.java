package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("message", "Welcome to My Spring Boot Web Application!");
        return "home";
    }
	
	@GetMapping("/health")
    @ResponseBody
    public String healthCheck() {
        return "OK";
    }
}
