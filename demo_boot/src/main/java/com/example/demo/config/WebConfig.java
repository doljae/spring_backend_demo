package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// http://jmlim.github.io/spring/2018/12/11/spring-boot-crossorigin/
	// https://velog.io/@hellozin/Spring-API-%EC%84%9C%EB%B2%84%EC%97%90%EC%84%9C-PUT-DELETE-%EC%9A%94%EC%B2%AD-%EC%8B%9C-CORS-%EC%84%A4%EC%A0%95%EC%9D%B4-%EC%A0%81%EC%9A%A9-%EC%95%88%EB%90%98%EB%8A%94-%EA%B2%BD%EC%9A%B0
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:8080")
		.allowedMethods(
				HttpMethod.GET.name(),
				HttpMethod.HEAD.name(),
				HttpMethod.POST.name(), 
				HttpMethod.PUT.name(), 
				HttpMethod.DELETE.name());
	}
}
