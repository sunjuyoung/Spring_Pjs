package com.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController  //순수한 데이터를 반환
@RequestMapping("/sample")
@Log4j
public class SampleController {

	
	
	//문자열을 반환
	//jsp파일이 아닌 순수한 데이터가 된다
	//메서드 내의 MediaType이라는 클래스를 이용해서 문자열을 보낼수 있다
	@GetMapping(value="/getText",produces = "text/plain; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
		
	}
	
	
	
	//xml , json 
	@GetMapping(value="/getSample",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		
		
		
		return new SampleVO(112,"스타","로드");
		
	}
	
	//produces 생략 가능
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		
		
		
		return new SampleVO(112,"스타","로드");
		
	}
	
	
	//list
	@GetMapping(value="/getList")
	public List<SampleVO> getList(SampleVO vo) {
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int a= 0; a<4; a++) {
			vo.setMno(a);
			vo.setFirstName("first"+a);
			vo.setLastName("last"+a);
			list.add(vo);
		}
		//return IntStream.range(1,10).mapToObj(i-> new SampleVO(i,i+"first",i+" last")).collect(Collectors.toList());
		
		return list;
	}
	
	//map
	@GetMapping(value="/getMap")
	public Map<String,SampleVO> getMap(SampleVO vo) {
		

		Map<String,SampleVO> map = new HashMap<>();
		
		map.put("first", new SampleVO(111,"나는","ㅈ달"));
		
		
		
		return map;
		
		
	}
	
	
	//반드시 height, weight 파라미터로 저달 받는다
	@GetMapping(value="/check",params = {"height","weight"})
	public ResponseEntity<SampleVO> check(int height, int weight){
		
		SampleVO vo = new SampleVO(0,""+height,""+weight);
		
		ResponseEntity<SampleVO> result = null;
		
		
		//상태코드와 데이터를 전달한다.
		if(height > 150) {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}
		
		return result;
	}
		
	
	
	
	
}
