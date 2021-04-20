package com.test.studycafe.repository;

import com.querydsl.jpa.JPQLQuery;
import com.test.studycafe.domain.QStudy;
import com.test.studycafe.domain.Study;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StudyRepositoryExtensionImpl extends QuerydslRepositorySupport implements StudyRepositoryExtension{


    public StudyRepositoryExtensionImpl() {
        super(Study.class);
    }

    @Override
    public List<Study> findByKeyword(String keyword) {
        QStudy study = QStudy.study;
        System.out.println("====================");
        JPQLQuery<Study> query = from(study).where(study.published.isTrue()
               .and(study.title.containsIgnoreCase(keyword))
                .or(study.tags.any().title.containsIgnoreCase(keyword))
                .or(study.zones.any().localName.containsIgnoreCase(keyword)));
        return query.fetch();
    }
}
