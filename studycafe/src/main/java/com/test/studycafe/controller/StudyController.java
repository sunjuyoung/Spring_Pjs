package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.StudyForm;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.StudyService;
import com.test.studycafe.valid.StudyFormValidator;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final StudyFormValidator studyFormValidator;

    @InitBinder("studyForm")
    public void StudyFormBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(studyFormValidator);
    }


    @GetMapping("/new-study")
    public String studyForm(@CurrentUser Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new StudyForm());
        return "study/form";
    }

    @PostMapping("/new-study")
    public String studySubmit(@CurrentUser Account account, @Valid StudyForm studyForm, Errors errors){
        //studyFormValidator.validate(studyForm,errors);
        if(errors.hasErrors()){
            return "study/form";
        }

       Study newStudy = studyService.createNewStudy(account,studyForm);
        return "redirect:/study/"+ URLEncoder.encode(newStudy.getPath(), StandardCharsets.UTF_8);
    }
}
