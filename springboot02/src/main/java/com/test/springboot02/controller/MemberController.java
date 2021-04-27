package com.test.springboot02.controller;

import com.test.springboot02.dto.SignUpDTO;
import com.test.springboot02.service.MemberService;
import com.test.springboot02.valid.SignUpValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SignUpValidator signUpValidator;

    @InitBinder("signUpDTO")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpValidator);

    }


    @GetMapping("/sign-up")
    public String signUpForm(@ModelAttribute SignUpDTO signUpDTO){
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String submitSignUp(@Valid SignUpDTO signUpDTO, Errors errors, Model model, RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
           model.addAttribute("message",errors.getFieldError().getDefaultMessage());
            return "sign-up";
        }

        memberService.createNewMember(signUpDTO);
        redirectAttributes.addFlashAttribute("message","회원가입 성공");
        return "redirect:/login";
    }
}
