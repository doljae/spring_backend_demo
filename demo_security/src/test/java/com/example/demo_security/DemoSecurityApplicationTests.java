package com.example.demo_security;

import com.example.demo_security.security.MemberUserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@EnableWebSecurity
class DemoSecurityApplicationTests {

    @Autowired
    MemberUserDetailService memberUserDetailService;
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Test
    void contextLoads() {
        System.out.println(memberUserDetailService.loadUserByUsername("seokjae"));
    }
    @Test
    void contextLoads2(){
        System.out.println(authenticationManagerBuilder.getDefaultUserDetailsService());
    }

}
