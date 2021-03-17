package com.test.studycafe.mail;

import com.test.studycafe.dto.EmailMessage;

public interface EmailService {

    void sendEmail(EmailMessage emailMessage);
}
