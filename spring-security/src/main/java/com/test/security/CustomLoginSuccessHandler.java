package com.test.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{//AuthenticationSuccessHandler 인터페이스 구현
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		
		log.warn("Login Success~");
	
		
		List<String> roleNames = new ArrayList<>();
		
		
		
		//로그인한 사용자에 부여된 권한 Authentication객체를 이용해서 사용자가 가진 모든 권한을 문자열로 체크
		//권한에 맞는 페이지로 이동
		auth.getAuthorities().forEach(authority -> {
		
			
			roleNames.add(authority.getAuthority());
			
		});
		
		log.warn("ROLE NAMES : "+ roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/sample/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/sample/member");
			return;
		}
		
		
		response.sendRedirect("/");
		
	}
	
	

}
