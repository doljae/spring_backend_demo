package com.example.demo.security;

public enum AppplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    AppplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
