package com.test.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	
	
	//인터페이스 메서드는 handle() 뿐 
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, 
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
	
		log.info("Access Denied Hnadler");
		
		log.error("Redirect......");
		
		//접근 제한에 걸리는 경우 리다이렉트 방식
		response.sendRedirect("/accessError");
	
		
	}
	
	
	

}
