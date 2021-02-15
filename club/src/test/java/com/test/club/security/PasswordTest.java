package com.test.club.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String password = "12341234";
        String enPw = passwordEncoder.encode(password);
        System.out.println("nePw :"+ enPw);

        boolean matchresult = passwordEncoder.matches(password,enPw);

        System.out.println(matchresult);
    }
}
