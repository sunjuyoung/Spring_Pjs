package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.BoardVO;
import com.test.domain.PageDTO;
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
	public void mainList(int pageNum, int amount,Model model) {
		
		
		int total = (int)(service.total());
		
		PageDTO dto = new PageDTO(pageNum,amount,total);
		
		model.addAttribute("page",dto );
	
		model.addAttribute("list",service.getListWithPaging(dto));
		
		
		
	}

	/**
	 * 
	 * @param bno
	 * @param model
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno")Long bno,Model model) {//조회페이지에서 목록 페이지로 이동 때 페이지 파라미터 
		                                         //@ModelAttribute 사용하지 않아도 controller에서 화면으로 파라미터가된객체는 전달된다 (명시적으로 지정함)
		
	model.addAttribute("board",service.get(bno));
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	/**
	 * 
	 * @param vo
	 * @param rttr
	 * @return
	 */
	@PostMapping(value="/register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		
		
		try {
			service.insert(vo);
			rttr.addFlashAttribute("result", "게시물 등록");
		}catch(Exception e) {
			rttr.addFlashAttribute("result","실패");
		}
		
		
		
		return "redirect:/board/mainList.do";
	}
	
	/**
	 * 
	 * @param vo
	 * @param rttr
	 * @return
	 */
	@PostMapping(value="/modify")
	public String modify(BoardVO vo,RedirectAttributes rttr ) {
		log.info("수정");
		int result = service.modify(vo);
		
		return "redirect:/board/get?bno="+vo.getBno();
	}
	
	/**
	 * 
	 * @param vo
	 * @param rttr
	 * @return
	 */
	@PostMapping(value="/remove")
	public String remove(BoardVO vo, RedirectAttributes rttr) {
		
		log.info("삭제");
		String result = "";
		if(service.remove(vo.getBno())) {
			result = vo.getBno()+"번 게시물 삭제 완료";
		}else {
			result = "삭제 실패";
		}
		
		rttr.addFlashAttribute("result",result);
		
		return "redirect:/board/mainList.do";
	}
}
