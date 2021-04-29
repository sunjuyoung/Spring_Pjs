package com.test.springboot02.controller;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.dto.PageRequestDTO;
import com.test.springboot02.dto.PageResultDTO;
import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.Member;
import com.test.springboot02.security.AuthMember;
import com.test.springboot02.security.CurrentUser;
import com.test.springboot02.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/list")
    public String list(@CurrentUser Member member, Model model,@ModelAttribute PageRequestDTO pageRequestDTO){
       PageResultDTO pageResultDTO= boardService.getList(pageRequestDTO);
        model.addAttribute("result",pageResultDTO);
        model.addAttribute("auth",member);
        return "board/list";
    }


    @GetMapping("/register")
    public String registerForm(@CurrentUser Member member,Model model,PageRequestDTO pageRequestDTO){
        model.addAttribute("auth",member);
        return "board/register";
    }

    @PostMapping("/register")
    public String registerBoard(@CurrentUser Member member,@Valid BoardDTO boardDTO, Errors errors, PageRequestDTO pageRequestDTO,
                                RedirectAttributes redirectAttributes,Model model){
        if(errors.hasErrors()){
            model.addAttribute("message",errors.toString());
            return "board/register";
        }
        boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("message","글 등록 완료");
        return "redirect:/board/list";
    }

    @GetMapping("/read/{bno}")
    public String readForm(@CurrentUser Member member,PageRequestDTO pageRequestDTO, @PathVariable Long bno,Model model){
        BoardDTO dto = boardService.getBoardByBno(bno);
        model.addAttribute("auth",member);
        model.addAttribute(dto);

        return "board/read";
    }

    @GetMapping("/modify/{bno}")
    public String modifyForm(@CurrentUser Member member,PageRequestDTO pageRequestDTO, @PathVariable Long bno,Model model){
        BoardDTO dto = boardService.getBoardByBno(bno);
        model.addAttribute("board",dto);
        model.addAttribute("auth",member);

        return "board/modify";
    }

    @PostMapping("/modify")
    public String modifySubmit(@CurrentUser Member member,BoardDTO boardDTO, PageResultDTO pageResultDTO,Model model,RedirectAttributes redirectAttributes){
        Long bno =  boardService.modify(boardDTO);
        model.addAttribute("auth",member);

        redirectAttributes.addFlashAttribute("message","수정완료");
        return "redirect:/board/read/"+bno;
    }

    @PostMapping("/remove")
    public String removeSubmit(@CurrentUser Member member,long bno,PageResultDTO pageResultDTO,RedirectAttributes redirectAttributes){
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("page",pageResultDTO.getPage());
        return "redirect:/board/list";
    }
}
