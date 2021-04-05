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

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final StudyRepository studyRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public Event createEvent(Study study, EventForm eventForm, Account account) {
       modelMapper.map(eventForm, Event.class);
       Event event = Event.builder()
               .createBy(account)
               .createdDateTime(LocalDateTime.now())
               .study(study)
               .build();
       return eventRepository.save(event);

    }
}
