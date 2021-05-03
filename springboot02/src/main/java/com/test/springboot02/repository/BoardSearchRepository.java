package com.test.springboot02.repository;

import com.test.springboot02.entity.Board;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardSearchRepository  {

    Board search1();

}
