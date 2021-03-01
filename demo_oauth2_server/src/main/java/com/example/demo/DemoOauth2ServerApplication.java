package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
public class DemoOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOauth2ServerApplication.class, args);
    }

}
