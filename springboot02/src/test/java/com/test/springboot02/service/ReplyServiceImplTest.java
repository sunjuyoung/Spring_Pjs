package com.test.springboot02.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceImplTest {

    @Autowired ReplyService replyService;

    @Test
    public void getListTest(){
        replyService.getList(299L);
    }

}