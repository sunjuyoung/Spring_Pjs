package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Service;

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

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


//스프링 MVC 이용해서 작성되는 controller특징
//httpServletRequest ,response를 거의 사용할 필요가 없다
//다양한 타입의 리턴타입
//get,post방식 어노테이션처리가능
//상속,인터페이스 방식대신 어노테이션만으로도 필요한 설정가능
//beans 규칙에 맞는 객체는 다시 화면으로 객체를 전달해준다(생성자가없거나 빈생성자,getter,setter 가진 클래스 객체들)


//파라미터 자동수집,자동변환 기능 request.getParameter() 생략

@Controller //bean객체등록 servlet-context.xml - context:compoenet-scan ,
@RequestMapping("/board")
@Log4j
@AllArgsConstructor 
public class BoardController {

	//위에 AllArgsConstructor 어노테이션 대신 @Autowired 사용해도된다
	private BoardService service;
	
	
	
	
	@GetMapping("/list")
	public void list(Model model, Criteria cri){
		
		List<BoardVO> list = new ArrayList<>();
		
		//Criteria cri = new Criteria();

		list = service.getListWithPaging(cri);
		
		int total = service.total(cri);
		
		model.addAttribute("pageMaker",new PageDTO(cri,total));
		
		model.addAttribute("list",list);
		
	
	}
	
	//입력
	//등록잡업은 POST방식이지만 화면에서 입력을 받아야 하므로 GET방식으로 입력페이즈를 볼수 있도록
	@GetMapping("/register")
	public void register() {
		
	}
	//입력
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		
		
		log.info("register,,,,,,,,,,,,,,,,,,,,,,,,,,"+board);
		service.register(board);
		rttr.addFlashAttribute("result",board.getBno());
		
		return "redirect:/board/list"; //재전송하지 않는다면 사용자는 브라우저의 새로고침을 통해서 동일한 내용을 계속 서버에 등록할수있기때문
		//경고창으로 할수있지만 근본적으로는 아예 URL 이동하는 방식이 좋다
		
	}
	//검색
	@GetMapping({"/get","/modify"})
	public void get(Long bno,Model model,@ModelAttribute("cri") Criteria cri) {//조회페이지에서 목록 페이지로 이동 때 페이지 파라미터 
		                                         //@ModelAttribute 사용하지 않아도 controller에서 화면으로 파라미터가된객체는 전달된다 (명시적으로 지정함)
		
	model.addAttribute("board",service.get(bno));
	}
	
	//수정
	@PostMapping("/modify") //화면은 get방식으로 접근하지만  실제 작업은 POST방식
	public String modify (BoardVO board, RedirectAttributes rttr,@ModelAttribute("cri") Criteria cri) {
		
		int result= service.modify(board); //service modify boolean으로 처리하여 if문으로 작성해도 됨
		
	
		rttr.addFlashAttribute("result", result);         //전달한 값은 url뒤에 붙지 않는다. 일회성 2개이상 쓸 경우, 데이터는 소멸 맵으로 전송
		rttr.addAttribute("pageNum", cri.getPageNum());  //전달한 값은 url뒤에  붙으며, 리프레시해도 데이터가 유지된다.
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("bno", board.getBno());
		return "redirect:/board/get";
	}
	
	 //삭제
	    //param 속성에 해당하는 bno를 읽어와 Long bno에 할당한다 ,파라미터 타입에 맞게 string값을 변환해 준다
		//옵션 defaultValue 로 값이 없을때 기본값 사용가능
		//required 필수 속성
	@PostMapping("/remove") 
	public String remove(@RequestParam("bno")Long bno,RedirectAttributes rttr , @ModelAttribute("cri") Criteria cri) {
		
		log.info("remove...................." + bno);
		
		 if(service.remove(bno)) {
			 rttr.addFlashAttribute("result","success");
		 }
		 
		 log.info("..........remove...................." + bno);
		 
		 rttr.addAttribute("pageNum",cri.getPageNum());
		 rttr.addAttribute("amount",cri.getAmount());
		 
		return "redirect:/board/list";
	}
	
	
	
	
}
