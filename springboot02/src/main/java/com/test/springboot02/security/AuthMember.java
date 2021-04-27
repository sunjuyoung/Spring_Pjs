package com.test.springboot02.security;

import com.test.springboot02.entity.Member;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AuthMember extends User {

    private Member member;

    public AuthMember(Member member) {
        super(member.getNickname(), member.getPassword(),member.getRoleSet().stream().map(role->
                new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet()));
        this.member = member;
    }
}
