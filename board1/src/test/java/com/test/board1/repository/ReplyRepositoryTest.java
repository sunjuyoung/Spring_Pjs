package com.test.board1.repository;

import com.test.board1.entity.Board;
import com.test.board1.entity.Member;
import com.test.board1.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void insertMember(){

        IntStream.rangeClosed(1,100).forEach(i->{
            long bno = (long)(Math.random()*100)+105;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .replyer("guest")
                    .board(board)
                    .text("reply..."+i)
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    public void testList(){
        Optional<Reply> result = replyRepository.findById(2L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getReplyer());
    }


    @Test
    public void testListOrder(){
        Board board = Board.builder().bno(106L).build();

       List<Reply> resutl =  replyRepository.getRepliesByBoardOrderByRno(board);

       resutl.forEach(reply -> {
           System.out.println(reply);
       });

    }


}