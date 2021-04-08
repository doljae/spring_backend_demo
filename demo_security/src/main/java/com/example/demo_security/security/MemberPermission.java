package com.example.demo_security.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@Getter
public enum MemberPermission {
    READ("user:read"),
    WRITE("user:write");

    private final String permission;
}
