package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/movie")
public class MovieController {

	
	@RequestMapping(value="/movieMain.do")
	public String movieMain(ModelMap model) {
		
		
		return "movie/movieMain";
	}
	
	
	@RequestMapping(value="/movieMain.json")
	public void movieList() {
		
	}
	
}
