package com.devkduck.jwt.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devkduck.jwt.filter.MyFilter1;
import com.devkduck.jwt.filter.MyFilter2;
import com.devkduck.jwt.filter.MyFilter3;


/*
 * Filter Test
 */
@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<MyFilter1> filter1(){
		FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
		bean.addUrlPatterns("/*");
		bean.setOrder(0); // 번호가 낮을 수록 먼저 호출
		return bean;
	}

	@Bean
	public FilterRegistrationBean<MyFilter2> filter2(){
		FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
		bean.addUrlPatterns("/*");
		bean.setOrder(1); // 번호가 낮을 수록 먼저 호출
		return bean;
	}
}
