package com.test.springboot02.repository;

import com.test.springboot02.entity.Member;
import com.test.springboot02.entity.MemberRole;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.internal.bytebuddy.utility.RandomString.make;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertUser(){
        IntStream.rangeClosed(1,100).forEach(i->{
            String s = RandomString.make(4);
            Member member = Member.builder()
                    .email("syseoz@naver.com")
                    .fromSocial(false)
                    .nickname("user"+i)
                    .password(passwordEncoder.encode("1234"))
                    .build();

            member.addMemberRole(MemberRole.USER);
            if(i>80){
                member.addMemberRole(MemberRole.MANAGER);
            }
            if(i>90){
                member.addMemberRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testUserRead(){
        Optional<Member> member = memberRepository.findByNicknameAndFromSocial("user1",false);
        Member getMember = member.get();
        System.out.println(getMember);

    }


}