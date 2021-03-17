package com.test.studycafe.mail;

import com.test.studycafe.dto.EmailMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Log4j2
@Profile("local")
@Component
public class ConsoleEmailService implements EmailService{
    @Override
    public void sendEmail(EmailMessage emailMessage) {
        log.info("send email : {}", emailMessage.getMessage());

    }
}
