package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;



    @DisplayName("가입후 로그인 테스트")
    @Test
    public void loginNickname() throws Exception{

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("syseoz@naver.com");
        signUpForm.setNickname("syseoz");
        signUpForm.setPassword("12341234");


        Account newAccount = accountService.newAccount(signUpForm);

        mockMvc.perform(post("/login")
                .param("username",newAccount.getNickname())
                .param("password","12341234")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated().withUsername("syseoz"));
    }

}