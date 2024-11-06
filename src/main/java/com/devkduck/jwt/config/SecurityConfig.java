package com.devkduck.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.devkduck.jwt.filter.MyFilter1;
import com.devkduck.jwt.filter.MyFilter3;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CorsFilter corsFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http.authorizeHttpRequests(
                authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                        .requestMatchers("/api/v1/user/**").hasAnyRole("USER", "MANAGER", "ADMIN") //인증만 되면 들어가도록 함
                        .requestMatchers("/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN") //자동으로 ROLE_접두어 생성해줌
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") //hasAnyRole은 하나라도 가지고 있으면 hasRole은 한가지
                        .anyRequest().permitAll()
        )
        .formLogin(login -> login.disable())
        .httpBasic(basic -> basic.disable())
        .csrf(csrf -> csrf.disable())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(new MyFilter3(),BasicAuthenticationFilter.class)
        .addFilter(corsFilter) // @CrossOrigin 인증이 없을때 , 인증이 있을때 필터에 등록 
        .build();
		
		
	}
	
	//session stateless , form login, httpbasic 사용안함
}
