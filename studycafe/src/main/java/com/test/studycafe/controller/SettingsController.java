package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.Notifications;
import com.test.studycafe.dto.PasswordForm;
import com.test.studycafe.dto.Profile;
import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.AccountService;
import com.test.studycafe.valid.PasswordFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    @InitBinder("passwordForm")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new PasswordFormValidator());
    }



    private final AccountService accountService;

    @GetMapping("/settings/profile")
    public String profileForm(@CurrentUser Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new Profile(account));

        return "settings/profile";

    }


    @PostMapping("/settings/profile")
    public String updateProfile(@CurrentUser Account account, @Valid Profile profile, Errors errors, Model model,
                                RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
            model.addAttribute(account);
            return "settings/profile";
        }

        accountService.updateProfile(account,profile);
        redirectAttributes.addFlashAttribute("message","프로필 수정 완료");
        return "redirect:/profile/"+account.getNickname();
    }

    @GetMapping("/settings/password")
    public String passwordForm(@CurrentUser Account account,Model model){
        model.addAttribute(account);
        model.addAttribute(new PasswordForm());

        return "settings/password";
    }

    @PostMapping("/settings/password")
    public String updatePassword(@CurrentUser Account account,@Valid PasswordForm passwordForm, Errors errors,
                                 Model model,RedirectAttributes redirectAttributes){

        if(errors.hasErrors()){
            model.addAttribute(account);
            return "settings/profile";
        }

        accountService.updatePassword(account,passwordForm);
        redirectAttributes.addFlashAttribute("message","패스워드 변경 완료");
        return "redirect:/profile/"+account.getNickname();
    }
    @GetMapping("/settings/notifications")
    public String notificationsForm(@CurrentUser Account account,Model model){
        model.addAttribute(account);
        model.addAttribute(new Notifications(account));

        return "settings/notifications";
    }

    @PostMapping("/settings/notifications")
    public String updateNotifications(@CurrentUser Account account,@Valid Notifications notifications, Errors errors,
                                 Model model,RedirectAttributes redirectAttributes){

        if(errors.hasErrors()){
            model.addAttribute(account);
            return "settings/profile";
        }

        accountService.updateNotifications(account,notifications);
        redirectAttributes.addFlashAttribute("message","알림설정 변경 완료");
        return "redirect:/profile/"+account.getNickname();
    }
}
