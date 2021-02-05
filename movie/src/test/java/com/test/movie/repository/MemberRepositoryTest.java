package com.test.movie.repository;

import com.test.movie.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

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
}