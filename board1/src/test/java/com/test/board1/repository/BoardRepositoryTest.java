package com.test.board1.repository;

import com.test.board1.entity.Board;
import com.test.board1.entity.Member;
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

    }

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

}