package com.test.board1.repository;

import com.test.board1.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

/*
    //한개의 로우Object 내에 Object[]로 나옴
    @Query("select b,w from Board b left join b.writer w where b.bno = :bno")
    Object getBoardWithWirter(@Param("bno") Long bno);


    @Query("select b,r from Board b left join Reply r  on b = r.board where b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno")Long bno);
*/

    //목록화면
    @Query(value="select b,w,count(r) " +
            "from Board b left join b.writer w " +
            "left join Reply r on r.board = b " +
            "group by b",
    countQuery="select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);


    //조회화면
    @Query("select b,w,count(r) " +
            "from Board b left join b.writer w " +
            "left join Reply r on r.board =b " +
            "where b.bno=:bno")
    Object getBoardByBno(@Param("bno")Long bno);
}
