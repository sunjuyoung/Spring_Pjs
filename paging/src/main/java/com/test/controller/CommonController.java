package com.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {
	

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("accessError");
		model.addAttribute("msg", auth.toString());
	}
	
	
	@GetMapping("/loginPage")
	public void loginPage( Model model) {
		log.info("loginPage");
		
	}
}
