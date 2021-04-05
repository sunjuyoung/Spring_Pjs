package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Event;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.EventForm;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.EventService;
import com.test.studycafe.service.StudyService;
import com.test.studycafe.valid.EventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study/{path}")
public class EventController {

    private final StudyService studyService;
    private final EventService eventService;
    private final EventValidator eventValidator;

    @InitBinder("eventForm")
    public void initbind(WebDataBinder webDataBinder){
        webDataBinder.addValidators(eventValidator);
    }

    @GetMapping("/new-event")
    public String newEventForm(@CurrentUser Account account, @PathVariable String path, Model model){
       Study study = studyService.getStudyToUpdateStatus(account,path);

       model.addAttribute(account);
       model.addAttribute(study);
       model.addAttribute(new EventForm());
       return "event/form";
    }

    @PostMapping("/new-event")
    public String submitEvent(@CurrentUser Account account, @PathVariable String path, Model model,
                              @Valid EventForm eventForm, Errors errors){
        Study study = studyService.getStudyToUpdateStatus(account,path);
        if(errors.hasErrors()){
            model.addAttribute(account);
            model.addAttribute(study);
            return "event/form";
        }
        Event event = eventService.createEvent(study,eventForm,account);
        return "redirect:/study/"+ URLEncoder.encode(study.getPath(), StandardCharsets.UTF_8)+"/events/"+event.getId();
    }
}
