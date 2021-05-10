package com.test.springboot02.service;

import com.test.springboot02.dto.SignUpDTO;
import com.test.springboot02.entity.Member;
import com.test.springboot02.entity.MemberRole;
import com.test.springboot02.mail.EmailMessage;
import com.test.springboot02.mail.EmailService;
import com.test.springboot02.repository.MemberRepository;
import com.test.springboot02.security.AuthMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
    private final EmailService emailService;
    //private final JavaMailSender javaMailSender;


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
        sendMail(member);
    }

    @Override
    public void sendMail(Member member) {
        EmailMessage emailMessage= EmailMessage.builder()
                .to(member.getEmail())
                .subject("가입 인증 메일")
                .message("")
                .build();

       emailService.sendEmail(emailMessage);


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
