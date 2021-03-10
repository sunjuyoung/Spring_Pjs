package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {


    Account newAccount(SignUpForm signUpForm);

    void login(Account newAccount);

    void signUpEmailSend(Account account);


    void completeSignUp(Account account);

    void updateProfile(Account account, Profile profile);

    void updatePassword(Account account, PasswordForm passwordForm);

    void updateNotifications(Account account, Notifications notifications);

    void updateNickname(Account account, NicknameForm nicknameForm);
}
