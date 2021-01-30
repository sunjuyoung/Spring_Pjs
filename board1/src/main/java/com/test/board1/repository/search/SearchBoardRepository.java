package com.test.board1.repository.search;

import com.test.board1.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

    Board search1();
    Board search2();
    Board search3();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
