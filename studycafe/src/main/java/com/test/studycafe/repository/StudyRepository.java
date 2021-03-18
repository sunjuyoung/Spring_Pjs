package com.test.studycafe.repository;

import com.test.studycafe.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface StudyRepository extends JpaRepository<Study,Long> {
    boolean existsByPath(String path);
}
