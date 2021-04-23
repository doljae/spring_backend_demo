package com.example.demo_security.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import sun.java2d.pipe.SpanShapeRenderer;

import static com.example.demo_security.security.MemberPermission.*;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum MemberRole {
    USER(Sets.newHashSet(READ)),
    ADMIN(Sets.newHashSet(READ, WRITE));

    @Getter
    private final Set<MemberPermission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        System.out.println(permissions);
        return permissions;
    }

}
