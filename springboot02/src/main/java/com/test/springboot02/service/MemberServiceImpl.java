package com.test.springboot02.service;

import com.test.springboot02.entity.Member;
import com.test.springboot02.repository.MemberRepository;
import com.test.springboot02.security.AuthMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        log.info("======================");

        Optional<Member> member = memberRepository.findByNicknameAndFromSocial(username,false);

        if(member.isEmpty()){
            throw  new UsernameNotFoundException(username);
        }
        Member member1 = member.get();

        log.info("======================");
        member1.getRoleSet().stream().forEach(i->{
           System.out.println(i.name());
        });


        return new AuthMember(member1,member1.getRoleSet().stream().map(role->
                new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet()));
    }
}
