package com.test.studycafe.repository;

import com.test.studycafe.domain.Study;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly=true)
public interface StudyRepository extends JpaRepository<Study,Long> {

    boolean existsByPath(String path);

    @EntityGraph(value="Study.withAll" ,type= EntityGraph.EntityGraphType.FETCH)
    Optional<Study> findByPath(String path);
}
