package com.test.studycafe.repository;

import com.test.studycafe.domain.Event;
import com.test.studycafe.domain.Study;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    @EntityGraph(value="Event.withEnrollments", type= EntityGraph.EntityGraphType.FETCH)
    List<Event> findAllByStudy(Study study);
}
