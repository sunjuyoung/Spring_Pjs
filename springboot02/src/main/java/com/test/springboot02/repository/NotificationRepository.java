package com.test.springboot02.repository;

import com.test.springboot02.entity.Member;
import com.test.springboot02.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    long countByMemberAndChecked(Member member, boolean checked);
}
