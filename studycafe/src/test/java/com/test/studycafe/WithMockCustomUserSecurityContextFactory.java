package com.test.studycafe;

import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    @Autowired
    private AccountService accountService;

    @Override
    public SecurityContext createSecurityContext(WithAccount account) {
        String nickname = account.value();

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("syseoz@naver.com");
        signUpForm.setNickname("syseoz");
        signUpForm.setPassword("12341234");
        accountService.newAccount(signUpForm);

        UserDetails principal = accountService.loadUserByUsername(nickname);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);


        return context;
    }
}
