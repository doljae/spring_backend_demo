package com.example.demo.jwt;


import com.example.demo.jwt.refreshtoken.RefreshTokenVo;
import com.example.demo.jwt.refreshtoken.RefreshTokenVoRepository;
import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final RefreshTokenVoRepository repository;

    public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig, RefreshTokenVoRepository repository) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Http 요청 header에서 "Authorization" 의 문자열값을 가져옴
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        // 위 filter에서 종료되지 않았으면 다음단계로 넘어감
        // https://github.com/jwtk/jjwt#reading-a-jws
        String userid = null;
        String token = null;
        try {
            token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build().parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            userid = body.getSubject();
            // 현재 토큰이 아무런 문제가 없을때
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
            // https://velog.io/@sa833591/Spring-Security-4-Authentication-SecurityContextHolder%EC%9D%98-%EC%9D%B4%ED%95%B4
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userid,
                    null,
                    simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            // 현재 토큰이 만료가 되면 리프레시 토큰이 있는지 찾아봄
            // 유효한 리프레시 토큰이 db에 있으면 새로 토큰을 발급해서 헤더에 넣어줌
            System.out.println(e);
            https:
//stackoverflow.com/questions/35791465/is-there-a-way-to-parse-claims-from-an-expired-jwt-token/35791515
            userid = e.getClaims().getSubject();
            System.out.println(userid);
            try {
                RefreshTokenVo refreshTokenVo = repository.findByUserid(userid);
                if (refreshTokenVo == null) {
                    throw new IllegalStateException("Refresh Token cannot be founded");
                }
                String refreshToken = refreshTokenVo.getTokenValue().replace(jwtConfig.getTokenPrefix(), "");
                Jws<Claims> refreshClaimsJws = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build().parseClaimsJws(refreshToken);
                Claims body = refreshClaimsJws.getBody();
                userid = body.getSubject();
                List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
                Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                        .map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userid,
                        null,
                        simpleGrantedAuthorities
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String newToken = generateToken(authentication);
                response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + newToken);
            }
            // 리프레시 토큰도 만료가 되었으면 리프레시 토큰을 지우고 예외처리 시킴
            catch (JwtException ee) {
                System.out.println(ee);
                userid = e.getClaims().getSubject();
                System.out.println(userid);
                int cnt = repository.deleteByUserid(userid);
                throw new IllegalStateException("Refresh Token cannot be trusted, do login again");
            }
            // 토큰 자체가 문제가 있으면 예외 처리 시킴
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token cannot be trusted", token));
        }
        // 아무런 문제가 없으면 다음 필터로 넘김
        filterChain.doFilter(request, response);
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
}
