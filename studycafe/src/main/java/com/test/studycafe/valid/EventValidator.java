package com.test.studycafe.valid;

import com.test.studycafe.dto.EventForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EventForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventForm eventForm = (EventForm)target;
        if(eventForm.getEndEnrollDateTime().isBefore(LocalDateTime.now()) ){
            errors.rejectValue("endEnrollDateTime","wrong.datetime","모임 접수 종료 일을 정확히 입력하세요.");
        }

        if(eventForm.getEndDateTime().isBefore(eventForm.getStartDateTime()) ||
                eventForm.getEndDateTime().isBefore(eventForm.getEndEnrollDateTime())){
            errors.rejectValue("endDateTime","wrong.datetime","모임 일을 정확히 입력하세요.");
        }

        if(eventForm.getStartDateTime().isAfter(eventForm.getEndDateTime()) ||
           eventForm.getStartDateTime().isBefore(eventForm.getEndEnrollDateTime()) ||
                eventForm.getStartDateTime().isBefore(LocalDateTime.now())){
            errors.rejectValue("startDateTime","wrong.datetime","시작모임 일을 정확히 입력하세요.");
        }

    }
}
