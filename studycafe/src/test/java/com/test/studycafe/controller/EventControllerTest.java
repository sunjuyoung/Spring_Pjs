package com.test.studycafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.studycafe.WithAccount;
import com.test.studycafe.domain.Enrollment;
import com.test.studycafe.repository.AccountRepository;
import com.test.studycafe.repository.EnrollmentRepository;
import com.test.studycafe.repository.TagRepository;
import com.test.studycafe.service.AccountService;
import com.test.studycafe.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    EventService eventService;
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Test
    @DisplayName("선착순 모임 참가, 자동 수락")
    public void newEnrollmentFCFS() throws Exception{


    }


}