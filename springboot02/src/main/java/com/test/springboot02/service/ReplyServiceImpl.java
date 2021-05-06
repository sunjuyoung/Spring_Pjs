package com.test.springboot02.service;

import com.test.springboot02.dto.ReplyDTO;
import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.Reply;
import com.test.springboot02.repository.BoardRepository;
import com.test.springboot02.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
            Board board = Board.builder().bno(replyDTO.getBno()).build();
            Reply reply = Reply.builder()
                    .board(board)
                    .text(replyDTO.getText())
                    .replyer(replyDTO.getReplyer())
                    .build();
        Reply save = replyRepository.save(reply);
        return save.getId();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        Board board = Board.builder().bno(bno).build();
       List<Reply> result =  replyRepository.getRepliesByBoardOrderById(board);
       List<ReplyDTO> dtoList = new ArrayList<>();
       result.stream().forEach(i->{
           dtoList.add(modelMapper.map(i,ReplyDTO.class));
       });
        return dtoList;
    }

    @Override
    public void remove(Long id) {
        replyRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void modify(Long id,String replyText) {
        Optional<Reply> byId = replyRepository.findById(id);
        Reply reply = byId.get();
        reply.setText(replyText);
        replyRepository.save(reply);
    }
}
