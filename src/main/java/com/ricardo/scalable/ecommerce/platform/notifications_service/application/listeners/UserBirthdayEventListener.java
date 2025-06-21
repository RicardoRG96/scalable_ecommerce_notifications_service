package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserBirthdayEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.MailService;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model.EmailRequest;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model.EmailTemplateType;
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
        Map<String, Object> variables = Map.of("name", event.getName());

        email.setTo(event.getEmail());
        email.setTemplateType(EmailTemplateType.USER_BIRTHDAY);
        email.setTemplateVariables(variables);

        mailService.sendEmail(email);
         
        logger.info("Birthday email sent to {}", event.getEmail());
    }

}
