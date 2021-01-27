package com.test.board1.service;

import com.test.board1.dto.BoardDTO;
import com.test.board1.entity.Board;
import com.test.board1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {

        Board board = dtoToEntity(dto);
        boardRepository.save(board);

        return board.getBno();
    }
}
