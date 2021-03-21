package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.DescriptionForm;
import com.test.studycafe.dto.StudyForm;
import com.test.studycafe.repository.StudyRepository;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final StudyFormValidator studyFormValidator;
    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;

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
    public String studySubmit(@CurrentUser Account account, @Valid StudyForm studyForm, Errors errors,Model model){
        //studyFormValidator.validate(studyForm,errors);
        if(errors.hasErrors()){
            model.addAttribute(account);
            return "study/form";
        }

       Study newStudy = studyService.createNewStudy(account,studyForm);
        return "redirect:/study/"+ URLEncoder.encode(newStudy.getPath(), StandardCharsets.UTF_8);
    }

    @GetMapping("/study/{path}")
    public String viewForm(@CurrentUser Account account,@PathVariable String path, Model model){
        model.addAttribute(account);

        model.addAttribute(studyRepository.findByPath(path));
        return "study/view";

    }

    @GetMapping("/study/{path}/members")
    public String members(@CurrentUser Account account,@PathVariable String path ,Model model){
        model.addAttribute(account);
        model.addAttribute(studyRepository.findByPath(path));

        return "study/members";
    }


    @GetMapping("/study/{path}/events")
    public String events(@CurrentUser Account account,@PathVariable String path ,Model model){
        model.addAttribute(account);
        model.addAttribute(studyRepository.findByPath(path));

        return "study/events";
    }

    @GetMapping("/study/{path}/settings/description")
    public String settingsDescription(@CurrentUser Account account,Model model,@PathVariable String path){
        Study study = studyRepository.findByPath(path);

        model.addAttribute(account);
        model.addAttribute(modelMapper.map(study,DescriptionForm.class));
        model.addAttribute(study);

        return "study/settings/description";


    }
}
