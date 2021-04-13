package com.example.demo_security.config;

import com.example.demo_security.security.MemberUserDetailService;
import com.example.demo_security.security.filter.AuthenticationFilter;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.demo_security.security.MemberRole.*;


import javax.crypto.SecretKey;

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


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();
        //.headers()
        //.frameOptions().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilter(new AuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                //.addFilterAfter(new JwtVerifyingFilter(), AuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console").permitAll()
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
