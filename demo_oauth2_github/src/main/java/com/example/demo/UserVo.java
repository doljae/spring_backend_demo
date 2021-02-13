package com.example.demo;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

// 연관관계가 양방향으로 묶여있을때 @Data 쓰면 stack overflow 에러 남
// 없으면 써도 됨
@Entity
@Data
@Table
public class UserVo {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String github;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String contactNumber;
    @NotEmpty
    private String memo;
    // enum은 NotEmpty대신 NotNull, EnumType.STRING으로 고정할 것
    @NotNull
    @Enumerated(EnumType.STRING)
    private TshirtSize size;
}
