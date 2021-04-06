package com.example.demo.jwt.refreshtoken;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
@Getter
@Setter
public class RefreshTokenVo {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String userid;
    @NotEmpty
    @Column(length = 100000)
    private String tokenValue;

    public RefreshTokenVo() {

    }

    public RefreshTokenVo(String username, String tokenValue) {
        this.userid = username;
        this.tokenValue = tokenValue;
    }


}
