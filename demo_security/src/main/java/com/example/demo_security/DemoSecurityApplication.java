package com.example.demo_security;

import com.example.demo_security.entity.Member;
import com.example.demo_security.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoSecurityApplication implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityApplication.class, args);
    }

    // test
    // https://pasudo123.tistory.com/394
    @Override
    public void run(String... args) throws Exception {
        List<Member> members = Arrays.asList(
                new Member("seokjae", "password"),
                new Member("scott", "tiger")
        );
        List<Member> savedMembers = memberRepository.saveAll(members);
        savedMembers.forEach(System.out::println);
    }
}
