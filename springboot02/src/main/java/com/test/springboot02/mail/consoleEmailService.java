package com.test.springboot02.mail;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Log4j2
//@Profile("local")
@Component
public class consoleEmailService{

/*
    @Override
    public void sendEmail(EmailMessage emailMessage) {
        log.info("sent email : {}" , emailMessage.getMessage());
    }*/
}
