package com.test.board.repository;

import com.test.board.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook,Long> {
}
