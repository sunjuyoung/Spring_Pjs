package com.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.Criteria;
import com.test.domain.ReplyPageDTO;
import com.test.domain.ReplyVO;
import com.test.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//REST방식 댓글 처리
//스프링4부터 @Restcontroller라는 어노테이션을 추가해서 해당 
//controller 메서드 들은 리턴 타입을   기존과 다르게처리한다는 것을 명시
// 컨트롤러 메소드 선언부에 @ResponseBody 이용한것과 동일
@RequestMapping("/replies")
@RestController  
@Log4j
@AllArgsConstructor
public class ReplyController {

	private ReplyService service;
	
	
	//입력
	@PostMapping(value="/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create (@RequestBody ReplyVO vo){
		
		log.info("ReplyVO,...................." + vo);
		int insertCount = service.insert(vo);
		
		log.info("insert....................."+insertCount);
		
		return insertCount == 1? new ResponseEntity<>("success",HttpStatus.OK):
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
	//목록
	@GetMapping(value= "/pages/{bno}/{page}",
			produces= {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") Long bno,@PathVariable("page") int page){
		
		Criteria cri = new Criteria(page,10);
		
		return new ResponseEntity<>(service.getListWithPaging(bno, cri),HttpStatus.OK);
		
	}
	
	
	//조회
	
	@GetMapping(value= "/{rno}",
			produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno")Long rno){
		
		log.info("get..........................."+rno);
		
		
		return new ResponseEntity<>(service.read(rno),HttpStatus.OK);
	}
	
	
	
	//삭제
	@DeleteMapping(value = "/{rno}",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno")Long rno){
		
	
		
		return service.remove(rno) == 1? new ResponseEntity<>("success",HttpStatus.OK):
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//수정
	@RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH},
			value="/{rno}",
			consumes = "application/JSON",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@RequestBody ReplyVO vo,@PathVariable("rno") Long rno){
		
		log.info(vo);
		log.info(rno);
		vo.setRno(rno); // requestBody로 처리되는 데이터는 일반 파라미터나 pathvariable 파라미터를 처리할 수없기 때문에 직접 처리
		
		log.info(vo);
		
		return service.update(vo) == 1? new ResponseEntity<>("success",HttpStatus.OK):
			new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
	
}
