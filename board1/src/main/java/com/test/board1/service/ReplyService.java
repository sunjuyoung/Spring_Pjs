package com.test.board1.service;

import com.test.board1.dto.ReplyDTO;
import com.test.board1.entity.Board;
import com.test.board1.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO dto);
    List<ReplyDTO> getList(Long bno);
    void modify(ReplyDTO dto);
    void remove(Long rno);


    default ReplyDTO entityToDTO(Reply reply){

        ReplyDTO dto = ReplyDTO.builder()
                .replyer(reply.getReplyer())
                .text(reply.getText())
                .modDate(reply.getModDate())
                .regDate(reply.getRegDate())
                .rno(reply.getRno())
                .build();

        return dto;
    }


    default Reply dtoToEntity(ReplyDTO dto){
        Board board = Board.builder().bno(dto.getBno()).build();

        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .replyer(dto.getReplyer())
                .text(dto.getText())
                .board(board)
                .build();

        return reply;
    }


}
