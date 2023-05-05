//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//	@Autowired
//	CustomFilter myCustomFilter;
//
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
//		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()).addFilterBefore(myCustomFilter,
//				UsernamePasswordAuthenticationFilter.class).csrf().disable();
//		return http.build();
//	}
//}