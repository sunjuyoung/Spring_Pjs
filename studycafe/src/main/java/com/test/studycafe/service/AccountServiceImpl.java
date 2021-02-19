package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

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


}
