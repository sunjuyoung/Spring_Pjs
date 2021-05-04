package com.test.springboot02.repository;

import com.test.springboot02.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardSearchRepository  {

    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
    Page<Object[]> searchPage1(String type, String keyword, Pageable pageable);

}
