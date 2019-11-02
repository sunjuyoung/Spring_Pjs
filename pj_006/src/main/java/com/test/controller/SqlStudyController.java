package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.domain.SqlVO;
import com.test.service.SqlStudyService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/sql")
@Log4j
public class SqlStudyController {

	@Autowired
	private SqlStudyService sqlService;
	
	
	@RequestMapping(value="/testList.do")
	public String test() {
		log.info("test.do controller");
		return "sql/testList";
		
	}
	
	@RequestMapping(value="/testList.json")
	public @ResponseBody SqlVO testList() {
		
		log.info("testList.json controller");
		
		return sqlService.testList();
		
	}
	
}
