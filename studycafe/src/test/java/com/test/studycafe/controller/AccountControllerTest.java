package com.test.studycafe.controller;

import com.test.studycafe.domain.Account;
import com.test.studycafe.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @MockBean
    JavaMailSender javaMailSender;


    @DisplayName("인증메일 입력값 오류")
    @Test
    public void checkEmailTokenWrong() throws Exception{

        mockMvc.perform(get("/check-email-token")
        .param("token","")
        .param("email",""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("error"))
                .andExpect(unauthenticated());;
    }

    @Transactional
    @DisplayName("인증메일 확인")
    @Test
    public void checkEmailToken() throws Exception{

        Account account = Account.builder()
                .nickname("test")
                .password("12341234")
                .email("test@test.com")
                .build();
        Account newAccount =  accountRepository.save(account);
        newAccount.generateEmailCheckToken();


        mockMvc.perform(get("/check-email-token")
                .param("token",newAccount.getEmailCheckToken())
                .param("email",newAccount.getEmail()))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("error"))
                .andExpect(model().attributeExists("nickname"))
                .andExpect(model().attributeExists("numberOfUser"))
                .andExpect(view().name("account/checked-email"))
                .andExpect(authenticated().withUsername("test"));
    }



    @Test
    public void signUpForm() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/sign-up"));

    }

    @Test
    public void signUpSubmit() throws Exception{
        mockMvc.perform(post("/sign-up")
                .param("nickname","syseoz")
                .param("email","syseoz@naver.com")
                .param("password","12341234")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        Account a = accountRepository.findByEmail("syseoz@naver.com");
        assertNotNull(a);
        assertNotEquals(a.getPassword(),"12341234");//패스워드 확인

        assertTrue(accountRepository.existsByEmail("syseoz@naver.com"));

    }


}