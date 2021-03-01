package com.example.demo.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Member implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    String name;
    String username;
    String remark;

    public Member() {
    }

    public Member(String name, String username, String remark) {
        this.name = name;
        this.username = username;
        this.remark = remark;
    }
}
