package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.repository.AccountRepository;
import com.test.studycafe.security.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Account newAccount(@Valid SignUpForm signUpForm) {

        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .studyCreatedByWeb(true)
                .studyUpdatedByWeb(true)
                .studyEnrollmentResultByWeb(true)
                .build();
        Account newAccount = accountRepository.save(account);
        newAccount.generateEmailCheckToken();
        signUpEmailSend(newAccount);

        return newAccount;
    }



    //회원 가입 인증 메일 전송
    public void signUpEmailSend(Account newAccount) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setText("/check-email-token?email="+newAccount.getEmail()+
                "&token="+newAccount.getEmailCheckToken());
        mailMessage.setSubject("스터디올래, 회원가입 인증");

        javaMailSender.send(mailMessage);
    }


    @Override
    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account), //첫번째 파라미터가 Principle
                account.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        );
        //authenticationMananger 하는 ROLE 주입을 직접한다

        SecurityContextHolder.getContext().setAuthentication(token);
        //인코딩된 패스워드만 접근이 가능하다
        //plain패스워드는 db에 저장도 안 하고 사용될 일도 없다

      /*  SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);*/
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(emailOrNickname);
        if(account == null){
            account = accountRepository.findByNickname(emailOrNickname);
        }
        if(account == null){
            log.info("iwfehowheoifhoh");
            throw new UsernameNotFoundException(emailOrNickname);
        }

        return new UserAccount(account);
    }
}