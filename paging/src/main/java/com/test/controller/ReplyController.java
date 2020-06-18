package com.test.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.Criteria;
import com.test.domain.ReplyVO;
import com.test.service.ReplyService;

import lombok.AllArgsConstructor;

@RequestMapping("/replies")
@RestController
@AllArgsConstructor
public class ReplyController {

	private ReplyService service;
	
	
	/**
	 * 등록
	 * @param vo
	 * @return
	 */
	@PostMapping(value = "/new",
			consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		int result = service.insert(vo);
		
		return result==1? new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * 목록
	 * @param page
	 * @param bno
	 * @return
	 */
	@GetMapping(value="/pages/{bno}/{page}")
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("page") int page,@PathVariable("bno")int bno) {
		Criteria cri = new Criteria(page,10);
		
		return new ResponseEntity<>(service.list(bno, cri),HttpStatus.OK);
	}
	
	/**
	 * 삭제
	 * @param rno
	 * @return
	 */
	@DeleteMapping(value="/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno")int rno){
		
		return service.delete(rno) == 1? new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 수정
	 * @param rno
	 * @param vo
	 * @return
	 */
	@PutMapping(value="/{rno}")
	public ResponseEntity<String> modify(@PathVariable("rno")int rno,@RequestBody ReplyVO vo){
		
		return service.modify(vo) ==1 ? new ResponseEntity<String>("success",HttpStatus.OK):
			new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
