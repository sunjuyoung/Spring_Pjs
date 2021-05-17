package com.test.studycafe.service;

import com.test.studycafe.domain.Notification;
import com.test.studycafe.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void readNotification(List<Notification> notifications) {
        notifications.forEach(i->{
            i.setChecked(true);
        });
        notificationRepository.saveAll(notifications);
    }
}
