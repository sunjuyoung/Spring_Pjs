package com.test.studycafe.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.test.studycafe.domain.QStudy;
import com.test.studycafe.domain.QTag;
import com.test.studycafe.domain.QZone;
import com.test.studycafe.domain.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StudyRepositoryExtensionImpl extends QuerydslRepositorySupport implements StudyRepositoryExtension{


    public StudyRepositoryExtensionImpl() {
        super(Study.class);
    }

    @Override
    public Page<Study> findByKeyword(String keyword, Pageable pageable) {
        QStudy study = QStudy.study;
        System.out.println("====================");
        JPQLQuery<Study> query = from(study).where(study.published.isTrue()
               .and(study.title.containsIgnoreCase(keyword))
                .or(study.tags.any().title.containsIgnoreCase(keyword))
                .or(study.zones.any().localName.containsIgnoreCase(keyword)))
                .leftJoin(study.tags, QTag.tag).fetchJoin()
                .leftJoin(study.zones, QZone.zone).fetchJoin()
                .distinct()
                ;
        JPQLQuery<Study> pageableQuery =  getQuerydsl().applyPagination(pageable,query);
        QueryResults<Study> fetchResult =  pageableQuery.fetchResults();
        return new PageImpl<>(fetchResult.getResults(),pageable,fetchResult.getTotal());
    }
}
