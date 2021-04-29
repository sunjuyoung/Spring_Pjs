package com.test.springboot02.repository;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, QuerydslPredicateExecutor<Board> {

    List<Board> findByBnoBetweenOrderByBnoDesc(Long from , Long to);
    Page<Board> findByBnoBetween(Long from, Long to, Pageable pageable);
    void deleteBoardByBnoLessThan(Long bno);


    Board findByBno(Long bno);


    void deleteByBno(Long bno);
}
