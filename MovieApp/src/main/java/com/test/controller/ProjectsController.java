package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/projects")
@Log4j
public class ProjectsController {
	
	
	
	@RequestMapping(value="/projectMain.do")
	public void projectMain() {
		
	}

	
	@RequestMapping(value="/counselList.do")
	public void counselList() {
		
	}
}
