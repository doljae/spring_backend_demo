package com.example.demo_security.entity;


import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
