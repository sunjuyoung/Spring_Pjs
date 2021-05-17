package com.test.studycafe.repository;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Transactional(readOnly=true)
    long countByAccountAndChecked(Account account, boolean checked);


    @Transactional
    List<Notification> findByAccountAndCheckedOrderByCreatedLocalDateTime(Account account,boolean checked);

    @Transactional
    void deleteByAccountAndChecked(Account account, boolean checked);


}
