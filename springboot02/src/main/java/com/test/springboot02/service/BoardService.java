package com.test.springboot02.service;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.dto.PageRequestDTO;
import com.test.springboot02.dto.PageResultDTO;
import com.test.springboot02.entity.Board;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO);

    default BoardDTO entityToDTO(Board board){
        BoardDTO dto = BoardDTO.builder()
                .content(board.getContent())
                .title(board.getTitle())
                .writer(board.getWriter())
                .bno(board.getBno())
                .modDate(board.getModDate())
                .regDate(board.getRegDate())
                .build();

        return dto;
    }

    BoardDTO getBoardByBno(Long bno);
}
