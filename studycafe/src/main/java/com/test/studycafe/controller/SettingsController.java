package com.test.studycafe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Tag;
import com.test.studycafe.domain.Zone;
import com.test.studycafe.dto.*;
import com.test.studycafe.repository.TagRepository;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.AccountService;
import com.test.studycafe.service.ZoneService;
import com.test.studycafe.valid.NicknameValidator;
import com.test.studycafe.valid.PasswordFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 프로필 수정
 */
@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final AccountService accountService;
    private final NicknameValidator nicknameValidator;
    private final TagRepository tagRepository;
    private final ZoneService zoneService;
    private final ObjectMapper objectMapper;

    @InitBinder("passwordForm")
    public void passwordInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new PasswordFormValidator());
    }



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

    /**
     * 패스워드
     * @param account
     * @param model
     * @return
     */
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

    /**
     * 알림
     * @param account
     * @param model
     * @return
     */
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

    /**
     * 계정
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/settings/account")
    public String accountForm(@CurrentUser Account account,Model model){
        model.addAttribute(account);
        model.addAttribute(new NicknameForm());

        return "settings/account";
    }

    @PostMapping("/settings/account")
    public String accountUpdate(@CurrentUser Account account,@Valid NicknameForm nicknameForm,Errors errors,
                                Model model,RedirectAttributes redirectAttributes){
        nicknameValidator.validate(nicknameForm,errors);

        if(errors.hasErrors()){
            model.addAttribute(account);
            model.addAttribute("message","해당 닉네임을 사용할 수 없습니다.");
            return "settings/account";
        }
        accountService.updateNickname(account,nicknameForm);

        redirectAttributes.addFlashAttribute("message","닉네임 변경 완료");
        return "redirect:/settings/profile";
    }

    /**
     * 관심분야 Tag
     * @param account
     * @param model
     * @return
     */
    @GetMapping("/settings/tags")
    public String tagsForm(@CurrentUser Account account,Model model) throws JsonProcessingException {
        Set<Tag> tags =  accountService.getTags(account);

        List<String> allTag = tagRepository.findAll().stream().map(a->a.getTitle()).collect(Collectors.toList());

        model.addAttribute(account);
        model.addAttribute("whitelist",objectMapper.writeValueAsString(allTag));
        model.addAttribute("tags",tags.stream().map(a->a.getTitle()).collect(Collectors.toList()));

        return "settings/tags";
    }
    @PostMapping("/settings/tags/add")
    @ResponseBody
    public ResponseEntity<String> addTag(@CurrentUser Account account, @RequestBody TagForm tagForm){
        String title = tagForm.getTagTitle();
        Tag tag = tagRepository.findByTitle(title);
        if(tag == null){
            tag = tagRepository.save(Tag.builder().title(title).build());
        }

        accountService.addTag(account,tag);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/settings/tags/remove")
    @ResponseBody
    public ResponseEntity<String> removeTag(@CurrentUser Account account,@RequestBody TagForm tagForm){
        String title = tagForm.getTagTitle();
        Tag tag = tagRepository.findByTitle(title);

        accountService.removeTag(account,tag);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/settings/zones")
    public String zoneForm(@CurrentUser Account account,Model model) throws JsonProcessingException {
       Set<Zone> zones =  accountService.getZone(account);
       List<String> zoneList =  zoneService.zoneList();

       model.addAttribute("zones",zones.stream().map(a->a.toString()).collect(Collectors.toList()));
       model.addAttribute("whitelist",objectMapper.writeValueAsString(zoneList));

        return "settings/zone";
    }

    @PostMapping("/settings/zones/add")
    @ResponseBody
    public ResponseEntity<String> addZone(@CurrentUser Account account,@RequestBody ZoneForm zones){


        accountService.addZone(account,zones);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/settings/zones/remove")
    @ResponseBody
    public ResponseEntity<String> removeZone(@CurrentUser Account account,@RequestBody ZoneForm zones){

        accountService.removeZone(account,zones);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
