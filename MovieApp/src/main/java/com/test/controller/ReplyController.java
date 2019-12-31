package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.ReplyVO;
import com.test.service.ReplyService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/reply")
@Log4j
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@PostMapping(value="/insert")
	public ResponseEntity<String> insert(@RequestBody ReplyVO vo){
		log.info("reply controller");
		int result = service.insert(vo);
		
		return result == 1? new ResponseEntity<>("success",HttpStatus.OK) 
							: new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	
	
	
	
	
}
