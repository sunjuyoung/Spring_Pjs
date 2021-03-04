package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.SignUpForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {


    Account newAccount(SignUpForm signUpForm);

    void login(Account newAccount);

     void signUpEmailSend(Account account);


}
