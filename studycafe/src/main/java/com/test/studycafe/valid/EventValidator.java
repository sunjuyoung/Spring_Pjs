package com.test.studycafe.valid;

import com.test.studycafe.dto.EventForm;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
@Log4j2
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EventForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventForm eventForm = (EventForm)target;

        if(eventForm.getEndEnrollDateTime().isBefore(LocalDateTime.now())){
            errors.rejectValue("endEnrollDateTime","wrong.datetime","모임 접수 종료 일을 정확히 입력하세요.");
        }

        if(eventForm.getEndDateTime().isBefore(eventForm.getStartDateTime())||
        eventForm.getEndDateTime().isBefore(eventForm.getEndEnrollDateTime())){
            errors.rejectValue("endDateTime","wrong.datetime","모임 일을 정확히 입력하세요.");
        }

        if(eventForm.getStartDateTime().isBefore(eventForm.getEndEnrollDateTime())){
            errors.rejectValue("startDateTime","wrong.datetime","시작모임 일을 정확히 입력하세요.");
        }
        if(eventForm.getLimitOfEnrollments()==null ||eventForm.getLimitOfEnrollments().intValue()<=0){
            errors.rejectValue("limitOfEnrollments","wrong.limit","모집인원은 0명이상 입력하세요.");
        }

    }
}
