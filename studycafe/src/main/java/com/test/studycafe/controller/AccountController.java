package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.repository.AccountRepository;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.AccountService;
import com.test.studycafe.valid.SignUpFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 회원가입
 */
@Controller
@RequiredArgsConstructor
@Log4j2
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;


    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model){
       model.addAttribute("signUpForm",new SignUpForm());
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors){
        //signUpFormValidator.validate(signUpForm,errors);
        if(errors.hasErrors()){
            return "account/sign-up";
        }
        Account newAccount = accountService.newAccount(signUpForm);
        accountService.login(newAccount);

        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model){
        Account account = accountRepository.findByEmail(email);

        if(account == null){
            model.addAttribute("error","wrong email");
            return "account/checked-email";
        }

        if(!account.getEmailCheckToken().equals(token)){
            model.addAttribute("error","wrong token");
            return "account/checked-email";
        }

        accountService.completeSignUp(account);

        model.addAttribute("numberOfUser",accountRepository.count());
        model.addAttribute("nickname",account.getNickname());

        return "account/checked-email";
    }

    @GetMapping("/check-email")
    public String checkEmail(@CurrentUser Account account, Model model){
        model.addAttribute("account",account);
        return "account/check-email";

    }

    @GetMapping("/resend-confirm-email")
    public String resendConfirmEmail(@CurrentUser Account account, Model model){
        if(!account.canSendConfirmEmail()){
            model.addAttribute("error","10분뒤에 다시 시도해주세요");
            model.addAttribute(account);
            return "account/check-email";
        }
        accountService.signUpEmailSend(account);
        return  "redirect:/";
    }

    @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, Model model, @CurrentUser Account account){
        Account byNickname = accountRepository.findByNickname(nickname);
        if(byNickname == null){
            throw new IllegalArgumentException();
        }

        model.addAttribute(byNickname);
        model.addAttribute("isOwner",byNickname.equals(account));
        return "account/profile";
    }

}
