package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Enrollment;
import com.test.studycafe.domain.Event;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.EventForm;
import com.test.studycafe.repository.EnrollmentRepository;
import com.test.studycafe.repository.EventRepository;
import com.test.studycafe.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final StudyRepository studyRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final EnrollmentRepository enrollmentRepository;

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
        event.acceptWaitingList();
        
        //eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow();
        List<Enrollment> enrollment = event.getEnrollments();
        for(Enrollment e: enrollment){
            enrollmentRepository.delete(e);
        }
        eventRepository.delete(event);
    }

    public void newEnrollment(Account account, Event event){
        Enrollment enrollment = Enrollment.builder()
                .account(account)
                .event(event)
                .enrolledAt(LocalDateTime.now())
                .accepted(event.isAbleToAcceptEnrollment())
                .build();
        event.getEnrollments().add(enrollment);
        enrollmentRepository.save(enrollment);
    }

    public void cancelEnrollment(Account account, Event event) {
       Enrollment enrollment = enrollmentRepository.findByAccountAndEvent(account,event);
       event.getEnrollments().remove(enrollment);
       enrollment.setEvent(null);
       enrollmentRepository.delete(enrollment);

       event.acceptFirstWaitingEnroll();
    }

    public void confirmEnrollment(Event event, Long enrollId) {
        if(event.isAcceptConfirmEnrollment()) {
            Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollId);
            enrollment.get().setAccepted(true);
            enrollment.get().setAttended(true);
        }
    }

    public void confirmEnrollmentCancel(Long enrollId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollId);
        enrollment.get().setAccepted(false);
        enrollment.get().setAttended(false);
    }
}
