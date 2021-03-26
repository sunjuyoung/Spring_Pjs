package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.*;
import com.test.studycafe.repository.StudyRepository;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.StudyService;
import com.test.studycafe.valid.StudyFormValidator;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(!study.isUseBanner()){
            study.setImage(study.defaultImage());
        }
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


    @PostMapping("/study/{path}/settings/banner/image")
    public String updateBannerImage(@CurrentUser Account account, Model model, String image,
                                    RedirectAttributes redirectAttributes,@PathVariable String path){
        System.out.println("iamge:"+image);
        Study study = studyService.updateBannerImage(path,image);



        return "redirect:/study/"+ URLEncoder.encode(study.getPath(), StandardCharsets.UTF_8);
    }


    @GetMapping("/study/{path}/settings/tags")
    public String settingsTags(@CurrentUser Account account,Model model,@PathVariable String path){
        Study study = studyService.getStudyByPath(path);
        if(!study.isUseBanner()){
            study.setImage(study.defaultImage());
        }
        model.addAttribute(account);
        model.addAttribute("study",study);
        model.addAttribute("tags",study.getTags().stream().map(a->a.getTitle()).collect(Collectors.toList()));
        return "study/settings/tags";
    }

    @GetMapping("/study/{path}/settings/zones")
    public String settingsZones(@CurrentUser Account account,Model model,@PathVariable String path){
        Study study = studyService.getStudyByPath(path);
        if(!study.isUseBanner()){
            study.setImage(study.defaultImage());
        }
        model.addAttribute(account);
        model.addAttribute("study",study);
        return "study/settings/zone";
    }

    @PostMapping("/study/{path}/settings/tags/add")
    @ResponseBody
    public ResponseEntity<String> addTag(@CurrentUser Account account, @RequestBody TagForm tagForm,
                                         @PathVariable String path){


        Study study = studyService.getStudyByPath(path);
        studyService.updateTag(study,tagForm);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/study/{path}/settings/zones/add")
    @ResponseBody
    public ResponseEntity<String> addZone(@CurrentUser Account account, @RequestBody ZoneForm zoneForm,
                                          @PathVariable String path){


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
