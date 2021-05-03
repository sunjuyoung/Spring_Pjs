package com.test.springboot02.repository;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, QuerydslPredicateExecutor<Board>,BoardSearchRepository {

    List<Board> findByBnoBetweenOrderByBnoDesc(Long from , Long to);
    Page<Board> findByBnoBetween(Long from, Long to, Pageable pageable);
    void deleteBoardByBnoLessThan(Long bno);

    Board findByBno(Long bno);

    void deleteByBno(Long bno);

    //Board 클래스에는 Member와의 연관관계를 맺고 있으므로 b.writer같은 형태로 사용
    //내부에 있는 엔티티를 이용할때는 left join 뒤에 on을 이용하지 않는다
    @Query("select b,w from Board b left join b.writer w where b.bno = :bno")
    Object getBoardWithWriter(@Param("bno")Long bno);

    @Query("select b,r from Board b left join Reply r on r.board = b where b.bno = :bno")
    List<Object[]> getBoardWithReplies(@Param("bno")Long bno);

    //목록화면 게시물,댓글 수
    @Query(value = "select b,w,count(r)" +
            " from Board b " +
            "left join b.writer w  " +
            "left join Reply r on r.board = b GROUP BY b,w", countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> findByBoardWithReplyCount(Pageable pageable);

    //조회화면
    @Query("select b,w,count(r) from Board b left join b.writer w " +
            "left join Reply r on b=r.board where b.bno=:bno group by w,b")
    Object getBoardWithRepliesAndMember(@Param("bno")Long bno);

    @Query("select b,w,count(r) from Board b left join b.writer w " +
            "left outer join Reply r on r.board = b where b.bno=:bno")
    Object getBoardByBno(@Param("bno") Long bno);



}
