package com.test.springboot02.service;

import com.test.springboot02.dto.BoardDTO;
import com.test.springboot02.dto.PageRequestDTO;
import com.test.springboot02.dto.PageResultDTO;
import com.test.springboot02.entity.Board;
import com.test.springboot02.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void test(){
        BoardDTO dto = new BoardDTO();
        dto.setTitle("sample title");
        dto.setContent("sample content");
        dto.setWriter("user0");

        System.out.println(boardService.register(dto));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).size(20).build();
        PageResultDTO<BoardDTO, Board> resultDTO = boardService.getList(pageRequestDTO);

        System.out.println("PREV:"+resultDTO.isPrev());
        System.out.println("NEXT"+resultDTO.isNext());
        System.out.println("TOTAL"+resultDTO.getTotalPage());

       for(BoardDTO dto:resultDTO.getDtoList()){
           System.out.println(dto);
       }
        System.out.println("========================");
       resultDTO.getPageList().forEach(i->{
           System.out.println("page:"+i);
       });

    }

}