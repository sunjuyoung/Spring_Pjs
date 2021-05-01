package com.test.springboot02.repository;

import com.test.springboot02.entity.Board;
import com.test.springboot02.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired ReplyRepository replyRepository;
    @Autowired MemberRepository memberRepository;


    @Test
    public void insertDummies(){
        for(int i=0; i<500; i++){
            long bno = (long)(Math.random()*300)+1;
            Board board = Board.builder().bno(bno).build();

            String nick = "user"+((i/10)+1);
            Reply reply = Reply.builder()
                    .replyer(nick)
                    .text("test reply.."+i)
                    .board(board)
                    .build();

            replyRepository.save(reply);

        }
    }

    @Transactional
    @Test
    public void testRead1(){
        Optional<Reply> result = replyRepository.findById(101L);
        Reply reply = result.get();
        System.out.println(reply.getReplyer());
        System.out.println(reply.getBoard());
    }

}