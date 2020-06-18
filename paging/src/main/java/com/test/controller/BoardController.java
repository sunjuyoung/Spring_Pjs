package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		int offset = (cri.getPageNum()-1)*cri.getAmount();
		cri.setOffset(offset);
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
	 * 글 작성 화면
	 */
	@GetMapping("/get")
	public void get(@RequestParam("bno") int bno,Model model) {
		
		model.addAttribute("board",service.get(bno));
		
	}
	
	/**
	 * 
	 * @param rttr
	 * @param board
	 * @return
	 */
	@PostMapping("/insert")
	public String insert(RedirectAttributes rttr,BoardVO board,@ModelAttribute("cri")Criteria cri) {
		log.info("insert");
		
		service.insert(board);
		rttr.addFlashAttribute("result" ,board.getBno());
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:/board/main";
	}
	

}
