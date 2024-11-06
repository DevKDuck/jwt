package com.devkduck.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//헤더 Authorization 안에 원하는 토큰 있나 확인
		if(req.getMethod().equals("POST")) {
			
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
				
			if(headerAuth.equals("devkduck")) {
				chain.doFilter(req, res);	
			}
			else {
				PrintWriter out = res.getWriter();
				out.println("POST 요청이지만 Authorization의 값이 다름");
			}

		}
				
	}
}
 
