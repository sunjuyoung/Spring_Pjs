package com.test.studycafe.service;

import com.test.studycafe.domain.Study;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface StudyServiceExtension {

    List<Study> findByKeyword(String keyword);
}
