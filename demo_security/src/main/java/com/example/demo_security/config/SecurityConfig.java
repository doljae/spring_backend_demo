package com.example.demo_security.config;

import com.example.demo_security.security.MemberUserDetailService;
import com.example.demo_security.security.filter.AuthenticationFilter;
import com.example.demo_security.security.filter.JwtVerifyingFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final MemberUserDetailService memberUserDetailService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(new AuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                //.addFilterAfter(new JwtVerifyingFilter(), AuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/h2-console").permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll());
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(memberUserDetailService);
        auth.authenticationProvider(daoAuthenticationProvider);
    }
}
