package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.domain.Criteria;
import com.test.domain.PagingDTO;
import com.test.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	
	//메인
	@GetMapping("/main")
	public void main() {
		
	}
	
	/*
	//리스트
	@GetMapping("/list")
	public void boardList(Model model) {
		
		log.info("list controller");
		
		model.addAttribute("list",service.getList());
		
		
	}
	*/
	
	//리스트
	@GetMapping("/list")
	public void pagingList(Model model,Criteria cri) {
		
		log.info("pagingList controller");
		
		int total = service.total();
		
	
		PagingDTO dto = new PagingDTO(cri,total);
		
		
		model.addAttribute("page",dto);
		model.addAttribute("list",service.pagingList(cri));
		
		
		
	}
	
	
	
	
	//등록 화면
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	//등록
	@PostMapping("/register")
	public String register(BoardVO vo,RedirectAttributes rttr) {
		
		log.info("register controller");
		
		int result = service.insert(vo);
		
		if(result > 0) {
			rttr.addFlashAttribute("result","등록");
		}
		
		//flashAttribute 일회성으로 데이터를 전달한다
		
		return "redirect:/board/list";
	}
	
	
	//조회,수정 화면
	@GetMapping({"/get","/modify"})
	public void get(Long bno,Model model ,@ModelAttribute("cri")Criteria cri) {//@RequsetParam 을 이용해서 명시적으로 처리할수있다
		
		log.info("get , modify controller");
		
		BoardVO board = service.read(bno);
		
		model.addAttribute("board" , board);
		
	}
	
	//수정
	@PostMapping("/modify")
	public String modify(BoardVO vo , RedirectAttributes rttr,@ModelAttribute("cri")Criteria cri) {
		
		
		if(service.update(vo) == 1) {
			rttr.addAttribute("result","수정");
		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:/board/list";
	}
	
	
	//삭제
	@PostMapping("/remove")
	public String remove(Long bno,RedirectAttributes rttr,@ModelAttribute("cri")Criteria cri) {
		
		int result= service.remove(bno);
		
		if(result > 0) {
			rttr.addFlashAttribute("result","삭제");
		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:/board/list";
	}
	
	

}
