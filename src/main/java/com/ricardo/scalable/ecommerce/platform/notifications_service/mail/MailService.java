package com.ricardo.scalable.ecommerce.platform.notifications_service.mail;

import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.dto.EmailRequest;

public interface MailService {

    void sendEmail(EmailRequest request);

}
