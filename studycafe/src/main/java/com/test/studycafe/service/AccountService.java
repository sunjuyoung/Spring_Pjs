package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.SignUpForm;

public interface AccountService {


    Account newAccount(SignUpForm signUpForm);

    void login(Account newAccount);

    // void signUpEmailSend(Account account);


}
