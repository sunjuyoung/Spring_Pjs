package com.test.board1.service;

import com.test.board1.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    ReplyService replyService;

    @Test
    public void replylist(){
        List<ReplyDTO> result =  replyService.getList(106L);

        for (ReplyDTO r:result){
            System.out.println(r);
        }

    }

}