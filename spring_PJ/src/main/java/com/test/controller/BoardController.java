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
import com.test.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	/**
	 * 
	 * @param model
	 */
	@GetMapping(value="/main")
	public void main(Model model) {
		List<BoardVO> list = service.boardList();
		
		model.addAttribute("list",list);
		
		
	}
	
	/**
	 * 추가
	 * @param vo
	 */
	@PostMapping("/insert")
	public void insert(BoardVO vo) {
		int result = service.insert(vo);
		log.info(result);
	}

	
	/**
	 * 삭제
	 * @param bno
	 */
	@PostMapping("/delete")
	public void delete(int bno,RedirectAttributes rttr) {
		int result = service.delete(bno);
		rttr.addFlashAttribute("result",result);
		log.info(result);
	}
}
