package com.test.studycafe.repository;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Enrollment;
import com.test.studycafe.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {


    Enrollment findByAccountAndEvent(Account account, Event event);
}
