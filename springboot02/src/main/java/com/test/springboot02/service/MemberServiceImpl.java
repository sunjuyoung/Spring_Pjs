package com.test.springboot02.service;

import com.test.springboot02.dto.SignUpDTO;
import com.test.springboot02.entity.Member;
import com.test.springboot02.entity.MemberRole;
import com.test.springboot02.repository.MemberRepository;
import com.test.springboot02.security.AuthMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void createNewMember(SignUpDTO signUpDTO) {
        Member member = Member.builder()
                .nickname(signUpDTO.getNickname())
                .email(signUpDTO.getEmail())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .fromSocial(false)
                .build();
        member.getRoleSet().add(MemberRole.USER);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByNicknameAndFromSocial(username,false);

        if(member.isEmpty()){
            throw  new UsernameNotFoundException(username);
        }
        Member member1 = member.get();

        return new AuthMember(member1);
    }
}
