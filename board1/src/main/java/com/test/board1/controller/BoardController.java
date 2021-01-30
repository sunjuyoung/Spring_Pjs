package com.test.board1.controller;

import com.test.board1.dto.BoardDTO;
import com.test.board1.dto.PageRequestDTO;
import com.test.board1.dto.PageResultDTO;
import com.test.board1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    /**
     * 목록화면
     * @param pageRequestDTO
     * @param model
     */
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResultDTO dto = boardService.getList(pageRequestDTO);

        model.addAttribute("result",dto);
    }

    @GetMapping("/register")
    public void register(){

    }

    /**
     * 글 등록
     * @param boardDTO
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, RedirectAttributes redirectAttributes){

        Long bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("bno",bno);

        return "redirect:/board/list";
    }


    /**
     * 조회 수정 화면
     * @param bno
     * @param model
     * @param pageRequestDTO
     */
    @GetMapping({"/read","/modify"})
    public void read(Long bno,Model model,PageRequestDTO pageRequestDTO){

       BoardDTO dto =  boardService.read(bno);
       model.addAttribute("dto",dto);

    }

    /**
     * 수정
     * @param boardDTO
     * @param model
     * @param pageRequestDTO
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO,Model model,PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes){

        boardService.modify(boardDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type",pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("bno",boardDTO.getBno());

        return "redirect:/board/read";
    }

    /**
     * 삭제
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/remove")
    public String remove(Long bno ,RedirectAttributes redirectAttributes){
        boardService.removeWithReplies(bno);

        return "redirect:/board/list";
    }


}
