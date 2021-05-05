package com.test.springboot02.service;

import com.test.springboot02.dto.ReplyDTO;
import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.Reply;
import com.test.springboot02.repository.BoardRepository;
import com.test.springboot02.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        return null;
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

    }

    @Override
    public void modify(Long id) {

    }
}
