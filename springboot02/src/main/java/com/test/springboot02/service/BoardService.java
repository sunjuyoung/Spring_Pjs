package com.test.springboot02.service;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.dto.PageRequestDTO;
import com.test.springboot02.dto.PageResultDTO;
import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto, Member member);

    Long modify(BoardDTO dto);

    void remove(Long bno);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO);

    default BoardDTO entityToDTO(Board board,Member member,Long count){
        BoardDTO dto = BoardDTO.builder()
                .content(board.getContent())
                .title(board.getTitle())
                .writer(member.getNickname())
                .bno(board.getBno())
                .modDate(board.getModDate())
                .regDate(board.getRegDate())
                .replyCount(count.intValue())
                .build();
        return dto;
    }
    default BoardDTO entityToDTO(Board board,Member member){
        BoardDTO dto = BoardDTO.builder()
                .content(board.getContent())
                .title(board.getTitle())
                .writer(member.getNickname())
                .bno(board.getBno())
                .modDate(board.getModDate())
                .regDate(board.getRegDate())

                .build();
        return dto;
    }



    BoardDTO getBoardByBno(Long bno,Member member);
}
