package com.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {
	

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("accessError"+auth);
		model.addAttribute("msg", "access denied");
	}
	
	
	@GetMapping("/loginPage")
	public void loginPage( Model model,String error,String logout) {
		
		
		log.info("loginPage");
		log.info("loginPage" + error);
		log.info("loginPage"+ logout);
		
		if(error != null) {
			model.addAttribute("error","Login Error");
		}
		if(logout != null) {
			model.addAttribute("logout","Logtout");
		}
		
		
	}
	
	
	@GetMapping("/logoutPage")
	public void logoutPage() {
		log.info("logoutPage");
	}
	
	@PostMapping("/logoutPage")
	public void logout() {
		log.info("logout");
	}
}
