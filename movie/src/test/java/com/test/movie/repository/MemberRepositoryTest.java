package com.test.movie.repository;

import com.test.movie.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void insert(){

        for(int i=1; i<100; i++){
            Member member = Member.builder()
                    .email("m"+i+"@naver.com")
                    .nickname("reviewer"+i)
                    .password("12341234")
                    .build();
            memberRepository.save(member);
        }
    }

    @Transactional
    @Commit
    @Test
    public void memberDelete(){

        Member member = Member.builder().mid(30L).build();
        reviewRepository.deleteByMember(member); //3개 리뷰가있는 사용자일경우 delete 3번 반복, @query 사용
        memberRepository.deleteById(30L);
    }
}