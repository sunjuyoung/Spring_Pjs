package com.test.board1.repository;

import com.test.board1.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository  extends JpaRepository<Reply,Long> {

    //댓글삭제(게시물삭제시)
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno")
    void deleteByBno(Long bno);
}
