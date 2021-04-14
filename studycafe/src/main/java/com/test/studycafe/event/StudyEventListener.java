package com.test.studycafe.event;

import com.test.studycafe.config.AppProperties;
import com.test.studycafe.domain.*;
import com.test.studycafe.dto.EmailMessage;
import com.test.studycafe.mail.EmailService;
import com.test.studycafe.repository.AccountRepository;
import com.test.studycafe.repository.NotificationRepository;
import com.test.studycafe.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Async
@Log4j2
@Transactional
@Component
@RequiredArgsConstructor
public class StudyEventListener {

    private final StudyRepository studyRepository;
    private final AccountRepository accountRepository;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final AppProperties appProperties;
    private final NotificationRepository notificationRepository;

    @EventListener
    public void handleStudyCreateEvent(StudyCreateEvent studyCreateEvent){
        Study study = studyRepository.findStudyWithTagAndZonesById(studyCreateEvent.getStudy().getId());

        Iterable<Account> accounts =  accountRepository.findAll(AccountPredicates.findByTagsAndZones(study.getTags(),study.getZones()));

       accounts.forEach(account -> {
           if(account.isStudyCreatedByEmail()){
               //이메일 전송
               Context context = new Context();
               context.setVariable("nickname",account.getNickname());
               context.setVariable("link","/study/");
               context.setVariable("linkName",study.getTitle());
               context.setVariable("message","새로운 스터디");
               context.setVariable("host",appProperties.getHost());

               String message = templateEngine.process("mail/simple-link",context);

               EmailMessage emailMessage = EmailMessage.builder()
                       .subject("스터디"+study.getTitle())
                       .to(account.getEmail())
                       .message(message)
                       .build();
               emailService.sendEmail(emailMessage);
           }
           if(account.isStudyCreatedByWeb()){
               //notification
               Notification notification = new Notification();
               notification.setTitle(study.getTitle());
               notification.setLink("/study/");
               notification.setChecked(false);
               notification.setCreatedLocalDateTime(LocalDateTime.now());
               notification.setMessage(study.getShortDescription());
               notification.setAccount(account);
               notification.setNotificationType(NotificationType.STUDY_CREATED);
               notificationRepository.save(notification);

           }
       });

        log.info(study.getTitle() +" is created");
        throw new RuntimeException();
    }
}
