package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserRegisteredEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.MailService;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.dto.EmailRequest;
import com.ricardo.scalable.ecommerce.platform.notifications_service.messaging.EventListener;

@Component
public class UserRegisteredEventListener implements EventListener<UserRegisteredEvent> {

    @Autowired
    private MailService mailService;

    private static final Logger logger = LoggerFactory.getLogger(UserRegisteredEventListener.class);

    @Override
    public void onEvent(UserRegisteredEvent event) {
        logger.info("User email: {}", event.getEmail());
        logger.info("User username: {}", event.getName());

        EmailRequest email = new EmailRequest();
        email.setTo(event.getEmail());
        email.setSubject("Welcome to Scalable E-commerce Platform");
        email.setBody(
            "Hello " + event.getName() + ",\n\n" +
            "Thank you for registering on our platform! We are excited to have you with us.\n\n" +
            "Best regards,\n" +
            "Scalable E-commerce Team"
        );

        mailService.sendEmail(email);
    }

}
