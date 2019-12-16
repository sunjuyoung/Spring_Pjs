package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@Setter(onMethod_  = {@Autowired})
	 private BoardService service;
	

	/**
	 * 
	 * @param model
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@RequestMapping(value="/mainList.do")
	public void mainList(Model model) {
		
	
		model.addAttribute("list",service.getList());
		
	}

	/**
	 * 
	 * @param bno
	 * @param model
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping({"/get","/modify"})
	public void get(Long bno,Model model) {//조회페이지에서 목록 페이지로 이동 때 페이지 파라미터 
		                                         //@ModelAttribute 사용하지 않아도 controller에서 화면으로 파라미터가된객체는 전달된다 (명시적으로 지정함)
		
	model.addAttribute("board",service.get(bno));
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping(value="/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		
		
		return "redirect:/board/mainList.do";
	}
	
}
