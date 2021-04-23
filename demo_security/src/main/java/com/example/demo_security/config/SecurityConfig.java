package com.example.demo_security.config;

import com.example.demo_security.security.MemberUserDetailService;
import com.example.demo_security.security.filter.AuthenticationFilter;
import com.example.demo_security.security.filter.JwtVerifyingFilter;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import static com.example.demo_security.security.MemberRole.*;


import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final MemberUserDetailService memberUserDetailService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, MemberUserDetailService memberUserDetailService, JwtConfig jwtConfig, SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.memberUserDetailService = memberUserDetailService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }


    // https://github.com/HomoEfficio/dev-tips/blob/master/Spring%20Security%EC%99%80%20h2-console%20%ED%95%A8%EA%BB%98%20%EC%93%B0%EA%B8%B0.md
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        //.ignoringAntMatchers("/h2-console/**");
        // https://gigas-blog.tistory.com/124
        // disable은 x-frame-options를 비활성화한다 -> 보안 취약
        // sameorigin은 동일 도메인에선 iframe 접근을 허용한다 -> localhost 테스팅용
        // 커스터마이징하면 아래와 같이쓴다.
        // 접속은 되는데 콘솔에서 에러가 뜨네;
        // 그냥 sameOrigin으로 해서 쓰고 나중에 다시 찾아봅시다
        http
                .headers()
                .frameOptions()
                .sameOrigin();
//                .addHeaderWriter(new StaticHeadersWriter("X-FRAME-OPTIONS", "ALLOW-FROM " + "localhost:8080/**"));
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //authenticated()는 인증되어야 한다는 뜻임
        //그러니깐 anyRequest()는 위에 antMatchers에 걸리는 URI 말고는 전부 인증되지않으면
        // 403 띄우라는 뜻입니다.
        http
                .addFilter(new AuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtVerifyingFilter(secretKey, jwtConfig), AuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated();
        //.formLogin(form -> form
        //        .loginPage("/login")
        //        .permitAll());


    }

//    @Bean
//    public AuthenticationManager getAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

    private DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(memberUserDetailService);
        return daoAuthenticationProvider;
    }
}
