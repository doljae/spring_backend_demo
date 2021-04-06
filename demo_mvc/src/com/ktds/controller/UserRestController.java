package com.ktds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktds.service.UserService;
import com.ktds.vo.UserVo;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    UserService service;

    @GetMapping("")
    public List<UserVo> getUsers() {
        List<UserVo> users = service.getUsers();
        return users;
    }

    @GetMapping("/{id}")
    public UserVo getUser(@PathVariable String id) {
        UserVo user = service.getUser(id);
        return user;
    }

    @PostMapping
    public int addUser(@RequestBody UserVo user) {
        int cnt = service.addUser(user);
        return cnt;
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable int id) {
        int cnt = service.deleteUser(id);
        return cnt;
    }

    @PutMapping
    public int updateUser(@RequestBody UserVo user) {
        int cnt = service.updateUser(user);
        return cnt;
    }
}
