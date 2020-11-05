package com.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;

//간단히 사용자가 알아볼 수 있는 에러 메시지만을 model에 추가
//Authentication 타입의 파라미터를 받도록 설계 해서 필요한 경우 사용자의 정보를 확인

@Controller
@Log4j
public class CommonController {

	//access denied 경우 403에러 메시지가 발생
	//jsp에서는 HttpServletRequest안에 SPRING_SECURITY_403_EXCEPTION이라는 이름으로 AccessDeniedException 객체가 전달
	
	
	  @GetMapping("/accessError") 
	  public void accessDenied(Authentication auth,Model model) {
	  
	  log.info("access denied : " + auth);
	  
	  model.addAttribute("msg", "Access Denied!");
	  
	  }
	 
	
	
	//반드시 get방식으로 접근하는 URI지정
	 @GetMapping("/customLogin")
	 public void loginInput(String error, String logout, Model model) {
		 
		 log.info("error : " + error);
		 
		 if(error != null) {
			 model.addAttribute("error", "Login Error Check Your Account");
			 
		 }
		 if(logout != null) {
			 model.addAttribute("logout","LogOut!!");
		 }
	 }
	
	 
	 @GetMapping("/customLogout")
	 public void logoutGET() {
		 
		 log.info("GET custom logout~~");
	 }
	 
	 @PostMapping("/customLogout")
	 public void logoutPOST() {
		 
		 log.info("Post custom logout~~");
	 }
	
}
