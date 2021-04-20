package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.repository.NotificationRepository;
import com.test.studycafe.repository.StudyRepository;
import com.test.studycafe.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final StudyRepository studyRepository;
    private final NotificationRepository notificationRepository;

    @GetMapping("/")
    public String home(@CurrentUser Account account, Model model){
            if(account != null){
                model.addAttribute(account);
                return "index";
            }


            return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/search/study")
    public String searchStudy(String keyword, Model model){
        List<Study> studyList= studyRepository.findByKeyword(keyword);
        model.addAttribute(studyList);
        model.addAttribute("keyword",keyword);

        return "search";
    }
}
