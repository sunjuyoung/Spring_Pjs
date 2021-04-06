package com.test.springboot02.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.QBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i->{
            Board board = Board.builder()
                    .title("Title : "+i)
                    .content("Content.."+i)
                    .writer("user"+(i%10))
                    .build();
            boardRepository.save(board);
        });
    }

    @DisplayName("단일 항목검색")
    @Test
    public void queryDslSearch1(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        QBoard qBoard = QBoard.board;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qBoard.title.contains(keyword);
        builder.and(expression);
        Page<Board> result = boardRepository.findAll(builder,pageable);
        for(Board board: result){
            System.out.println(board);
        }

    }
    @DisplayName("다중 항목 검색")
    @Test
    public void queryDslSearch2(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        QBoard qBoard = QBoard.board;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qBoard.title.contains(keyword);
        BooleanExpression exContent = qBoard.content.contains(keyword);
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);
        builder.and(qBoard.bno.gt(0L));

        Page<Board> result = boardRepository.findAll(builder,pageable);

        for(Board board: result){
            System.out.println(board);
        }

    }

    @Test
    public void testQueryMethod(){
        List<Board> result = boardRepository.findByBnoBetweenOrderByBnoDesc(70L,80L);

        for(Board board : result){
            System.out.println(board);
        }
    }
    @Test
    public void queryMethodWithPageable(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByBnoBetween(70L,80L,pageable);

        for(Board board : result){
            System.out.println(board);
        }
    }

    @Transactional
    @Test
    public void queryMethodDelete(){
     boardRepository.deleteBoardByBnoLessThan(4L);


    }
}