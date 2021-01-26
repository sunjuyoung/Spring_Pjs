package com.test.board1.repository;

import com.test.board1.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository  extends JpaRepository<Reply,Long> {
}
