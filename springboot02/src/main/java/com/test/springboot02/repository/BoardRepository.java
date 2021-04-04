package com.test.springboot02.repository;

import com.test.springboot02.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardRepository extends JpaRepository<Board,Long>, QuerydslPredicateExecutor<Board> {
}
