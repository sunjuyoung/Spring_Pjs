package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.PageDTO;
import com.test.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/")
@Log4j
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/main")
	public String main(Model model,Criteria cri) {
		log.info("basic");
		
		List<BoardVO> board = service.getList(cri);
		
		model.addAttribute("board",board);
		model.addAttribute("page",new PageDTO(cri,41));
		return "/board/main";
		
	}
	
	

}
