package com.test.studycafe.controller;

import com.test.studycafe.WithAccount;
import com.test.studycafe.domain.Account;
import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.repository.AccountRepository;
import com.test.studycafe.service.AccountService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

/*    @BeforeEach
    void before(){
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("syseoz@naver.com");
        signUpForm.setNickname("syseoz");
        signUpForm.setPassword("12341234");
        accountService.newAccount(signUpForm);
        System.out.println("+========================");
    }*/

    @AfterEach
    void after(){
        accountRepository.deleteAll();
    }


    //@WithUserDetails(value = "syseoz", setupBefore = TestExecutionEvent.TEST_EXECUTION) //2.3.x junit5 에서 오류
    @WithAccount("syseoz")
    @Test
    public void profileUpdate() throws Exception{
        mockMvc.perform(post("/settings/profile")
                .param("bio","소개 테스트")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("message"));

        Account ac = accountRepository.findByNickname("syseoz");

        assertEquals(ac.getBio(),"소개 테스트");
    }

    @DisplayName("프로필 수정 에러")
    @WithAccount("syseoz")
    @Test
    public void profileUpdateError() throws Exception{
        String bio = "35자 넘는다~ 35자 넘는다~35자 넘는다~35자 넘는다~35자 넘는다~35자 넘는다~35자 넘는다~35자 넘는다~ 테스트소개 테스트소개 테스트소개 테스트";
        mockMvc.perform(post("/settings/profile")
                .param("bio",bio)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/profile"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().hasErrors());

        Account ac = accountRepository.findByNickname("syseoz");
        assertNull(ac.getBio());

    }

}