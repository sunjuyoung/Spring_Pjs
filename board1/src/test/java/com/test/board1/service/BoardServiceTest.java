package com.test.board1.service;

import com.test.board1.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServceTest {

    @Autowired
    private BoardService service;


    @Test
    public void register(){

        BoardDTO dto = BoardDTO.builder()
                                .title("테스트1")
                .content("테스트컨")
                .writerEmail("user11@test.com")
                .build();

        Long id = service.register(dto);

        System.out.println(id);
    }

}