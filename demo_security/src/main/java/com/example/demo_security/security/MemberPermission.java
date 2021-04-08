package com.example.demo_security.security;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MemberPermission {
    READ("user:read"),
    WRITE("user:write");

    @Getter
    private final String permission;
}
