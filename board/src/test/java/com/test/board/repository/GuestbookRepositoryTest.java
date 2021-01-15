package com.test.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.test.board.entity.Guestbook;
import com.test.board.entity.QGuestbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Guestbook guestbook = Guestbook.builder()
                                            .title("title..."+i)
                                            .content("content.."+i)
                                            .writer("user"+(i%10))
                                            .build();
            guestbookRepository.save(guestbook);
        });

    }

    @Test void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(99L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();

            guestbook.changeTitle("change title");
            guestbook.chagneContent("change content");

            guestbookRepository.save(guestbook);
        }
    }

    @DisplayName("단일 항목 검색")
    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook; //q도메인 클래스생성

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();//where문에 들어가는 조건을 넣어주는 컨테이너

        BooleanExpression expression = qGuestbook.title.contains(keyword); //원하는 조건은 필드 값과 결합해서 생성


        builder.and(expression); //where 문에 and

        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);

        for(Guestbook g : result){
            System.out.println(g);
        }

    }
    @DisplayName("다중 항목 검색")
    @Test
    public void testQuery2(){
        //제목 혹은 내용에 "1"이 포함되어 있고, gno는 0보다 크다
        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
        BooleanExpression exContent = qGuestbook.content.contains(keyword);
        BooleanExpression exAll = exContent.or(exTitle);
        builder.and(exAll);
        builder.and(qGuestbook.gno.gt(2L));

        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);

       result.stream().forEach(g->{
           System.out.println(g);
       });

    }

}