package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserBirthdayEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.MailService;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.dto.EmailRequest;
import com.ricardo.scalable.ecommerce.platform.notifications_service.messaging.EventListener;

@Component
public class UserBirthdayEventListener implements EventListener<UserBirthdayEvent> {

    @Autowired
    private MailService mailService;

    private static final Logger logger = LoggerFactory.getLogger(UserBirthdayEventListener.class);

    @Override
    public void onEvent(UserBirthdayEvent event) {
        logger.info("User email: {}", event.getEmail());
        logger.info("User username: {}", event.getName());

        EmailRequest email = new EmailRequest();
        email.setTo(event.getEmail());
        email.setSubject("Happy Birthday from Scalable E-commerce Platform");
        email.setBody(
            "Hello " + event.getName() + ",\n\n" +
            "Wishing you a very happy birthday! We hope you have a wonderful day filled with joy and celebration.\n\n" +
            "Best wishes,\n" +
            "Scalable E-commerce Team"
        );

        mailService.sendEmail(email);
        logger.info("Birthday email sent to {}", event.getEmail());
    }

}
