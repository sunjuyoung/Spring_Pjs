package com.test.studycafe.service;

import com.test.studycafe.domain.Study;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class StudyServiceExtensionImpl extends QuerydslRepositorySupport implements StudyServiceExtension{


    public StudyServiceExtensionImpl() {
        super(Study.class);
    }

    @Override
    public List<Study> findByKeyword(String keyword) {

        return null;
    }
}
