package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/**
	 * 
	 * @param model
	 * @param cri
	 * @return
	 */
	@GetMapping("/main")
	public String main(Model model,Criteria cri) {
		log.info("basic");
		
		List<BoardVO> board = service.getList(cri);
		int total = service.total();
		
		model.addAttribute("board",board);
		model.addAttribute("page",new PageDTO(cri,total));
		return "/board/main";
		
	}
	
	/**
	 * 글 작성 화면
	 */
	@GetMapping("/insert")
	public void insert() {
		
	}
	
	/**
	 * 
	 * @param rttr
	 * @param board
	 * @return
	 */
	@PostMapping("/insert")
	public String insert(RedirectAttributes rttr,BoardVO board,Model model,Criteria cri) {
		log.info("insert");
		
		service.insert(board);
		rttr.addFlashAttribute("result" ,board.getBno());
		model.addAttribute("pageNum","1");
		model.addAttribute("amount","10");
		
		return "redirect:/board/main";
	}
	

}
