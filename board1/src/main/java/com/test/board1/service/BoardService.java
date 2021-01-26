package com.test.board1.service;

import com.test.board1.dto.BoardDTO;
import com.test.board1.entity.Board;
import com.test.board1.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto){
        Member member  = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .writer(member)
                .content(dto.getContent())
                .title(dto.getTitle())
                .build();

        return board;
    }
}
