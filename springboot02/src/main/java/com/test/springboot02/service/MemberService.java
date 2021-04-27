package com.test.springboot02.service;

import com.test.springboot02.dto.SignUpDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface MemberService extends UserDetailsService {

    void createNewMember(SignUpDTO signUpDTO);
}
