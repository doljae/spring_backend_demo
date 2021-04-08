package com.example.demo_security;

import com.example.demo_security.security.MemberUserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoSecurityApplicationTests {

    @Autowired
    MemberUserDetailService memberUserDetailService;

    @Test
    void contextLoads() {
        System.out.println(memberUserDetailService.loadUserByUsername("seokjae"));
    }

}
