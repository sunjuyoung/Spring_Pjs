package com.test.studycafe.repository;

import com.test.studycafe.domain.Study;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly=true)
public interface StudyRepository extends JpaRepository<Study, Long>, StudyRepositoryExtension {

    boolean existsByPath(String path);

    @EntityGraph(value="Study.withAll" ,type= EntityGraph.EntityGraphType.FETCH)
    Optional<Study> findByPath(String path);

    @EntityGraph(value="Study.withTagsAndManagers", type= EntityGraph.EntityGraphType.FETCH)
    Study findStudyWithTagByPath(String path); // == findByPath

    @EntityGraph(attributePaths={"managers"},type= EntityGraph.EntityGraphType.FETCH)
    Study findStudyWithManagersByPath(String path);

    @EntityGraph(attributePaths={"zones","managers"})
    Study findStudyWithZonesByPath(String path);

    Study findStudyOnlyByPath(String path);

    boolean existsByTitle(String newTitle);

    @EntityGraph(attributePaths={"members"},type= EntityGraph.EntityGraphType.FETCH)
    Study findStudyWithMembersByPath(String path);

    @EntityGraph(value="Study.withTagsAndZones",type= EntityGraph.EntityGraphType.FETCH)
    Study findStudyWithTagAndZonesById(Long id);
}
