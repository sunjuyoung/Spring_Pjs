package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Event;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.EventForm;
import com.test.studycafe.repository.EventRepository;
import com.test.studycafe.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final StudyRepository studyRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public Event createEvent(Study study, EventForm eventForm, Account account) {
       Event event = Event.builder()
               .createBy(account)
               .createdDateTime(LocalDateTime.now())
               .study(study)
               .title(eventForm.getTitle())
               .description(eventForm.getDescription())
               .endDateTime(eventForm.getEndDateTime())
               .endEnrollDateTime(eventForm.getEndEnrollDateTime())
               .startDateTime(eventForm.getStartDateTime())
               .eventType(eventForm.getEventType())
               .limitOfEnrollments(eventForm.getLimitOfEnrollments())
               .build();
       return eventRepository.save(event);

    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public List<Event> getEventList(Study study) {
        List<Event> events =  eventRepository.findAllByStudy(study);

        return events;
    }

    public void updateEvent(Event event, EventForm eventForm) {
        modelMapper.map(eventForm,event);
        //eventRepository.save(event);
    }
}
