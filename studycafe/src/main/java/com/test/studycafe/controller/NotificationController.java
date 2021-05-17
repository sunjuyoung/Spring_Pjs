package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Notification;
import com.test.studycafe.repository.NotificationRepository;
import com.test.studycafe.security.CurrentUser;
import com.test.studycafe.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;


    @GetMapping("/notifications")
    public String getNotifications(@CurrentUser Account account, Model model){
        List<Notification> notifications = notificationRepository.findByAccountAndCheckedOrderByCreatedLocalDateTime(account, false);
        long numberOfChecked = notificationRepository.countByAccountAndChecked(account, true);
        categorizedNotification(model,notifications,numberOfChecked,notifications.size());
        model.addAttribute("isNew",true);
        notificationService.readNotification(notifications);
        return "notification/list";
    }

    @GetMapping("/notifications/old")
    public String getOldNotifications(@CurrentUser Account account, Model model){
        List<Notification> notifications = notificationRepository.findByAccountAndCheckedOrderByCreatedLocalDateTime(account, true);
        long numberOfNotChecked = notificationRepository.countByAccountAndChecked(account, false);
        categorizedNotification(model,notifications,notifications.size(),numberOfNotChecked);
        model.addAttribute("isNew",false);
        notificationService.readNotification(notifications);
        return "notification/list";
    }

    private void categorizedNotification(Model model, List<Notification> notifications, long numberOfChecked, long numberOfNotChecked) {

        List<Notification> newStudyNotification = new ArrayList<>();
        List<Notification> eventEnrollmentNotification = new ArrayList<>();
        List<Notification> watchingStudyNotification = new ArrayList<>();

        for(var notification: notifications){
            switch (notification.getNotificationType()){
                case STUDY_CREATED:newStudyNotification.add(notification);break;
                case STUDY_UPDATE:watchingStudyNotification.add(notification);break;
                case EVENT_ENROLLMENT:eventEnrollmentNotification.add(notification);break;
            }
        }
        model.addAttribute("numberOfChecked",numberOfChecked);
        model.addAttribute("numberOfNotChecked",numberOfNotChecked);
        model.addAttribute("notifications",notifications);
        model.addAttribute("newStudyNotifications",newStudyNotification);
        model.addAttribute("eventEnrollmentNotifications",eventEnrollmentNotification);
        model.addAttribute("watchingStudyNotifications",watchingStudyNotification);

    }

}
