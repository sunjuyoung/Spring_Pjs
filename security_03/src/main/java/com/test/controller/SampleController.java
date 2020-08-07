package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*")
@Controller
public class SampleController {
	
	@GetMapping("/all")
	public void doAll() {
		log.info("all access");
	}
	
	@GetMapping("/member")
	public void member() {
		log.info("member access");
	}
	
	@GetMapping("/admin")
	public void admin() {
		log.info("admin access");
	}

}
