package com.test.studycafe.repository;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    long countByAccountAndChecked(Account account, boolean checked);
}
