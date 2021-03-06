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
@Component
@Transactional
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

        //account에서 study와 맞는 tag,zone 조회 (querydsl)
        Iterable<Account> accounts =  accountRepository.findAll(AccountPredicates.findByTagsAndZones(study.getTags(),study.getZones()));

       accounts.forEach(account -> {
           //이메일 전송
           if(account.isStudyCreatedByEmail()){
               Context context = new Context();
               context.setVariable("nickname",account.getNickname());
               context.setVariable("link","/study/"+study.getPath());
               context.setVariable("linkName",study.getTitle());
               context.setVariable("message","새로운 스터디");
               context.setVariable("host",appProperties.getHost());

               String message = templateEngine.process("simple-link",context);

               EmailMessage emailMessage = EmailMessage.builder()
                       .subject("스터디"+study.getTitle()+" 스터디가 생겼습니다.")
                       .to("syseoz@naver.com")
                       .message(message)
                       .build();
               emailService.sendEmail(emailMessage);
           }
           //웹으로 전송
           if(account.isStudyCreatedByWeb()){
               Notification notification = new Notification();
               notification.setTitle(study.getTitle());
               notification.setLink("/study/"+study.getPath());
               notification.setChecked(false);
               notification.setCreatedLocalDateTime(LocalDateTime.now());
               notification.setMessage(study.getShortDescription());
               notification.setAccount(account);
               notification.setNotificationType(NotificationType.STUDY_CREATED);
               notificationRepository.save(notification);
           }
       });
        log.info(study.getTitle() +" is created");
    }
}
