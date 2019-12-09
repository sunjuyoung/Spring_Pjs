package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.service.MainService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/main")
@Log4j
public class MainController {

	@Autowired
	private MainService service;
	
	
	/**
	 * 
	 * @param model
	 * @param rttr
	 * @return
	 */
	@RequestMapping(value="/main.do")
	public String main(ModelMap model, RedirectAttributes rttr) {
		
		
		//model.addAttribute("list",service.main());
		return "main/main";
	}
	

}
