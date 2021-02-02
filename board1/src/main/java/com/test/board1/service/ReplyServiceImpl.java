package com.test.board1.service;

import com.test.board1.dto.ReplyDTO;
import com.test.board1.entity.Board;
import com.test.board1.entity.Reply;
import com.test.board1.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;


    @Override
    public Long register(ReplyDTO dto) {
        replyRepository.save(dtoToEntity(dto));
        return dto.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        log.info("service----------------------------------");

        Board board = Board.builder().bno(bno).build();

        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(board);

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO dto) {
        replyRepository.save(dtoToEntity(dto));

    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
