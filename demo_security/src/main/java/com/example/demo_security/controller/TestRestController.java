package com.example.demo_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @GetMapping("/api/test")
    public String apiTest() {
        System.out.println("controller");
        return "this page is for ADMIN";
    }
}
