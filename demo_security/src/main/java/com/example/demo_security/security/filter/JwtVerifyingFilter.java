package com.example.demo_security.security.filter;

import com.example.demo_security.config.JwtConfig;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtVerifyingFilter extends OncePerRequestFilter {
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());
        // AUTHORIZATION 헤더가 비어있지않고, 그 헤더의 prefix가 지정한 prefix와 같아면
        // 토큰을 파싱해 Authentication 객체를 만들어 SecurityContext에 넣어준다.
        if (!Strings.isNullOrEmpty(authorizationHeader) && authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            try {
                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build().parseClaimsJws(token);
                Claims body = claimsJws.getBody();
                String username = body.getSubject();
                // 이 부분이 리팩토링이 필요함
                List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
                Set<SimpleGrantedAuthority> simpleGrantedAuthorities =
                        authorities
                                .stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
                                .collect(Collectors.toSet());
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        simpleGrantedAuthorities
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);


            } catch (JwtException e) {
                throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
            }
        }
        // 아무튼 다음 스텝으로 넘겨야하니깐 꼭 적어줄 것
        filterChain.doFilter(request, response);
    }
}
