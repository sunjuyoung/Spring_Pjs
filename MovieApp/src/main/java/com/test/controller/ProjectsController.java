package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectsController {
	
	
	@RequestMapping(value="/projectMain.do")
	public void projectMain() {
		
	}

	
	@RequestMapping(value="/counselList.do")
	public void counselList() {
		
	}
}
