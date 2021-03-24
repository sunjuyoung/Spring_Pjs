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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final StudyFormValidator studyFormValidator;
    private final ModelMapper modelMapper;

    @InitBinder("studyForm")
    public void StudyFormBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(studyFormValidator);
    }


    /**
     * 스터디 개설
     * @param account
     * @param model
     * @return
     */
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

    /**
     * 스터디 소개
     * @param account
     * @param path
     * @param model
     * @return
     */
    @GetMapping("/study/{path}")
    public String viewForm(@CurrentUser Account account,@PathVariable String path, Model model){
        model.addAttribute(account);

        model.addAttribute(studyService.getStudyByPath(path));
        return "study/view";

    }

    @GetMapping("/study/{path}/members")
    public String members(@CurrentUser Account account,@PathVariable String path ,Model model){
        model.addAttribute(account);
        model.addAttribute(studyService.getStudyByPath(path));

        return "study/members";
    }


    @GetMapping("/study/{path}/events")
    public String events(@CurrentUser Account account,@PathVariable String path ,Model model){
        model.addAttribute(account);
        model.addAttribute(studyService.getStudyByPath(path));

        return "study/events";
    }

    /**
     *  소개
     * @param account
     * @param model
     * @param path
     * @return
     */
    @GetMapping("/study/{path}/settings/description")
    public String settingsDescription(@CurrentUser Account account,Model model,@PathVariable String path){
       Study study = studyService.getStudyByPath(path);

        model.addAttribute(account);
        model.addAttribute(modelMapper.map(study,DescriptionForm.class));
        model.addAttribute("study",study);

        return "study/settings/description";
    }

    @PostMapping("/study/{path}/settings/description")
    public String updateDescription(@CurrentUser Account account, @Valid DescriptionForm descriptionForm, Errors errors,
                                    @PathVariable String path,
                                    Model model, RedirectAttributes redirectAttributes){

        Study study = studyService.getStudyByPath(path);

        if(errors.hasErrors()){
            model.addAttribute(study);
            model.addAttribute(account);
            return "study/settings/description";
        }
        studyService.updateDescription(study,descriptionForm);
        redirectAttributes.addFlashAttribute("message","소개를 수정했습니다.");

        return "redirect:/study/"+ URLEncoder.encode(study.getPath(), StandardCharsets.UTF_8);

    }

    /**
     * 배너
     * @param account
     * @param model
     * @param path
     * @return
     */
    @GetMapping("/study/{path}/settings/banner")
    public String settingsBanner(@CurrentUser Account account,Model model,@PathVariable String path){
        Study study = studyService.getStudyByPath(path);
        study.setImage(study.getImage());

        model.addAttribute(account);
        model.addAttribute("study",study);
        return "study/settings/banner";
    }

    @PostMapping("/study/{path}/settings/banner/enable")
    public String enableBanner(@CurrentUser Account account,Model model,@PathVariable String path ,
                               RedirectAttributes redirectAttributes){
        Study study = studyService.getStudyByPath(path);
        studyService.banner(study);

        return "redirect:/study/"+ URLEncoder.encode(study.getPath(), StandardCharsets.UTF_8);


    }
    @PostMapping("/study/{path}/settings/banner/disable")
    public String disableBanner(@CurrentUser Account account,Model model,@PathVariable String path ){
        Study study = studyService.getStudyByPath(path);
        studyService.banner(study);

        return "redirect:/study/"+ URLEncoder.encode(study.getPath(), StandardCharsets.UTF_8);


    }


}
