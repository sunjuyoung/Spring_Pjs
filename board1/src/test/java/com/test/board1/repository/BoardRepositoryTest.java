package com.test.board1.repository;

import com.test.board1.dto.BoardDTO;
import com.test.board1.dto.PageRequestDTO;
import com.test.board1.dto.PageResultDTO;
import com.test.board1.entity.Board;
import com.test.board1.entity.Member;
import com.test.board1.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepoitory;
    @Autowired
    BoardService service;


    @Test
    public void insertBoard(){

        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder().email("user"+i+"@test.com").build();

            Board board = Board.builder()
                    .title("title.."+i)
                    .content("content.."+i)
                    .writer(member)
                    .build();
            boardRepoitory.save(board);
        });
    }

/*
    @Test
    public void BoardWithMember(){
        //Optional<Board> result = boardRepoitory.findById(105L);
        //Board board = result.get();

        Object result1=  boardRepoitory.getBoardWithWirter(105L);
        Object[] arr = (Object[]) result1;

        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void boardWithReply(){

        List<Object[]> result=  boardRepoitory.getBoardWithReply(115L);
       for(Object[] a : result){
           System.out.println(Arrays.toString(a));
       }

    }*/

    @DisplayName("목록화면")
    @Test
    public void boardWithReplyCount(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepoitory.getBoardWithReplyCount(pageable);

        result.get().forEach(row->{
            Object[] arr = (Object[])row;
            System.out.println(Arrays.toString(arr));
        });

    }
    @DisplayName("조회화면")
    @Test
    public void boardByBno(){

        Object result = boardRepoitory.getBoardByBno(115L);

        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));

    }

    @DisplayName("목록화면 페이지")
    @Test
    public void boardWithPage(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO,Object[]> arr = service.getList(pageRequestDTO);

        for( BoardDTO ar : arr.getDtoList()){
            System.out.println(ar);
        }
    }


    @DisplayName("조회화면")
    @Test
    public void read(){
        BoardDTO dto =  service.read(115L);

        System.out.println(dto);
    }

    @DisplayName("삭제 댓글 포함")
    @Test
    public void removeWithReplies(){
        service.removeWithReplies(204L);
    }


    @DisplayName("수정")
    @Test
    public void modify(){
        BoardDTO dto = BoardDTO.builder()
                .bno(115L)
                .content("테스트 수정")
                .title("테스트 수정")
                .build();

        service.modify(dto);
    }
}