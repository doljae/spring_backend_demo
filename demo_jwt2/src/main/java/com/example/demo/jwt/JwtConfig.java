package com.example.demo.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;
import java.security.Key;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;
    private Integer refreshTokenExpirationAfterDays;

    public JwtConfig() {

    }

    public SecretKey getSecretKeyForSigning() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
