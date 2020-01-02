package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.PageDTO;
import com.test.domain.ReplyVO;
import com.test.service.ReplyService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/reply")
@Log4j
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/insert", consumes = "application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> insert(@RequestBody ReplyVO vo){
		log.info("reply controller");
		int result = service.insert(vo);
		
		return result == 1? new ResponseEntity<>("success",HttpStatus.OK) 
							: new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	
	
	@GetMapping(value="/list/{bno}/{pageNum}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno ,@PathVariable("pageNum") int pageNum){
		log.info("reply list");
		int total = service.total(bno);
		
		PageDTO dto = new PageDTO(pageNum,5,total);
		
		
		return new ResponseEntity<>(service.list(bno),HttpStatus.OK);
		
		
	}
	
	
	
	
}
