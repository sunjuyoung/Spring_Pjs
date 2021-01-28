package com.test.board1.service;

import com.test.board1.dto.BoardDTO;
import com.test.board1.dto.PageRequestDTO;
import com.test.board1.dto.PageResultDTO;
import com.test.board1.entity.Board;
import com.test.board1.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO,Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO read(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDTO dto);

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

    default BoardDTO entityToDTO(Board board,Member member, Long replyCount){

        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .regDate(board.getRegDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return dto;
    }
}
