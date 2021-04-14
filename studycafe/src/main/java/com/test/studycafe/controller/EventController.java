package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Enrollment;
import com.test.studycafe.domain.Event;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.EventForm;
import com.test.studycafe.repository.StudyRepository;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.EventService;
import com.test.studycafe.service.StudyService;
import com.test.studycafe.valid.EventValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/study/{path}")
public class EventController {

    private final StudyService studyService;
    private final EventService eventService;
    private final EventValidator eventValidator;
    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;

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

    @GetMapping("/events/{id}")
    public String getEvent(@CurrentUser Account account, @PathVariable String path, @PathVariable Long id,
                           Model model){
        Study study = studyService.getStudyByPath(path);
        Event event = eventService.getEvent(id);
        model.addAttribute(account);
        model.addAttribute(event);
        model.addAttribute(study);

        return "event/view";
    }

    @GetMapping("/events")
    public String eventsForm(@CurrentUser Account account, @PathVariable String path, Model model){
        Study study = studyService.getStudyByPath(path);
        List<Event> events =  eventService.getEventList(study);
        List<Event> newEvents=new ArrayList<>();
        List<Event> oldEvents=new ArrayList<>();
        for(Event e: events){
            if(e.getEndDateTime().isBefore(LocalDateTime.now())){
                oldEvents.add(e);
            }else{
                newEvents.add(e);
            }
        }
        model.addAttribute("newEvents",newEvents);
        model.addAttribute("oldEvents",oldEvents);
        model.addAttribute(account);
        model.addAttribute(study);

        return "study/events";
    }

    /**
     * 모임 수정
     * @param account
     * @param path
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/events/{id}/edit")
    public String updateEventForm(@CurrentUser Account account, @PathVariable String path, @PathVariable Long id,
                           Model model){
        Study study = studyService.getStudyByPath(path);
        Event event = eventService.getEvent(id);
        model.addAttribute(account);
        model.addAttribute(event);
        model.addAttribute(study);
        model.addAttribute(modelMapper.map(event,EventForm.class));

        return "event/update-form";
    }
    @PostMapping("/events/{id}/edit")
    public String updateEvent(@CurrentUser Account account, @PathVariable String path, @PathVariable Long id,
                              @Valid EventForm eventForm,Errors errors, Model model){
        Study study = studyService.getStudyByPath(path);
        Event event = eventService.getEvent(id);
        if(errors.hasErrors()){
            model.addAttribute(account);
            model.addAttribute(event);
            model.addAttribute(study);
            return "event/update-form";

        }
        eventService.updateEvent(event,eventForm);
        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/"+event.getId();
    }

    /**모임 삭제
     */
    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@CurrentUser Account account, @PathVariable String path, @PathVariable Long id){
        Study study = studyService.getStudyToUpdateStatus(account,path);
        eventService.deleteEvent(id);
        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/";

    }

    /**
     *모임 가입 신청
     */
    @PostMapping("/events/{id}/enroll")
    public String enrollEvent(@CurrentUser Account account, @PathVariable String path, @PathVariable("id") Event event,
                              Model model){
        Study study = studyService.getStudyOnlyByPath(path);
        eventService.newEnrollment(account,event);

        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/"+event.getId();
    }

    /**
     *모임 가입 신청 취소
     */
    @PostMapping("/events/{id}/disenroll")
    public String disEnrollEvent(@CurrentUser Account account, @PathVariable String path, @PathVariable("id") Event event){
        Study study = studyService.getStudyOnlyByPath(path);
        eventService.cancelEnrollment(account,event);
        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/"+event.getId();
    }

    /**
     *모임(CONFIRM) 대기인원 확정
     */
    @GetMapping("/events/{id}/enrollments/{enrollId}/accept")
    public String acceptEnroll(@CurrentUser Account account, @PathVariable String path, @PathVariable("id") Event event,
                               @PathVariable Long enrollId){
        Study study = studyService.getStudyOnlyByPath(path);
        eventService.confirmEnrollment(event,enrollId);
        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/"+event.getId();
    }

    /**
     * 모임(CONFIRM) 확정인원 취소
     */
    @GetMapping("/events/{id}/enrollments/{enrollId}/reject")
    public String rejectEnroll(@CurrentUser Account account, @PathVariable String path, @PathVariable("id") Event event,
                               @PathVariable Long enrollId){
        Study study = studyService.getStudyOnlyByPath(path);

        eventService.confirmEnrollmentCancel(enrollId);
        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/"+event.getId();
    }


    @GetMapping("/events/{id}/enrollments/{enrollId}/checkin")
    public String checkinEnroll(@CurrentUser Account account, @PathVariable String path, @PathVariable("id") Event event,
                               @PathVariable Long enrollId){
        Study study = studyService.getStudyOnlyByPath(path);

        eventService.confirmEnrollmentCancel(enrollId);
        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/"+event.getId();
    }

    @GetMapping("/events/{id}/enrollments/{enrollId}/cancel-checkin")
    public String cancelCheckinEnroll(@CurrentUser Account account, @PathVariable String path, @PathVariable("id") Event event,
                               @PathVariable Long enrollId){
        Study study = studyService.getStudyOnlyByPath(path);
        eventService.confirmEnrollmentCancel(enrollId);
        return "redirect:/study/"+URLEncoder.encode(study.getPath(),StandardCharsets.UTF_8)+"/events/"+event.getId();
    }

}
