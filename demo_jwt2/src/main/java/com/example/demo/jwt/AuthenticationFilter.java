package com.example.demo.jwt;

import com.example.demo.jwt.refreshtoken.RefreshTokenVo;
import com.example.demo.jwt.refreshtoken.RefreshTokenVoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final RefreshTokenVoRepository repository;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                JwtConfig jwtConfig,
                                SecretKey secretKey, RefreshTokenVoRepository repository) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.repository = repository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );
            System.out.println(authentication);
            Authentication authenticate = authenticationManager.authenticate(authentication);
            System.out.println(authenticate);
            return authenticate;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // token과 refresh token을 만듦

        String token = generateToken(authResult);
        String refreshToken = generateRefreshToken(authResult);
        // token은 헤더에 넣어줌
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
        // refresh token은 데이터베이스에 넣어둠

        // refresh token은 jpa를 이용해 db에 저장함
        String userid = authResult.getName();
        RefreshTokenVo refreshTokenVo = new RefreshTokenVo(userid, refreshToken);
        repository.save(refreshTokenVo);
    }

    private String generateToken(Authentication authResult) {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new java.util.Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        return token;
    }

    private String generateRefreshToken(Authentication authResult) {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new java.util.Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getRefreshTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        return token;
    }
}
