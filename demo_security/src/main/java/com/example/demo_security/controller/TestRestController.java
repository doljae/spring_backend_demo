package com.example.demo_security.controller;

import com.example.demo_security.model.LoginRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestRestController {
    @GetMapping("/api/test")
    public String apiTest() {
        System.out.println("controller");
        return "this page is for ADMIN";
    }

    @PostMapping("/login")
    public String loginSuccess(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        return "로그인에 성공했습니다!";
    }

}
