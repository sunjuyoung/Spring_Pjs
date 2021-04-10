package com.test.springboot02.controller;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.dto.PageRequestDTO;
import com.test.springboot02.dto.PageResultDTO;
import com.test.springboot02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/list")
    public String list(Model model, PageRequestDTO pageRequestDTO){
       PageResultDTO pageResultDTO= boardService.getList(pageRequestDTO);
        model.addAttribute("result",pageResultDTO);
        return "board/list";
    }


    @GetMapping("/register")
    public String registerForm(PageRequestDTO pageRequestDTO){

        return "board/register";
    }

    @PostMapping("/register")
    public String registerBoard(@Valid BoardDTO boardDTO, Errors errors, PageRequestDTO pageRequestDTO,
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
    public String readForm(PageRequestDTO pageRequestDTO, @PathVariable Long bno,Model model){
        BoardDTO dto = boardService.getBoardByBno(bno);
        model.addAttribute(dto);

        return "board/read";
    }
}
