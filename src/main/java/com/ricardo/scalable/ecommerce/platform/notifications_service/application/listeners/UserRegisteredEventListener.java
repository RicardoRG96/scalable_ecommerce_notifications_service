package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserRegisteredEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.MailService;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model.EmailRequest;
import com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model.EmailTemplateType;
import com.ricardo.scalable.ecommerce.platform.notifications_service.messaging.EventListener;

@Component
public class UserRegisteredEventListener implements EventListener<UserRegisteredEvent> {

    @Autowired
    private MailService mailService;
    
    @Value("${app.url}")
    private String appUrl;

    private static final Logger logger = LoggerFactory.getLogger(UserRegisteredEventListener.class);

    @Override
    public void onEvent(UserRegisteredEvent event) {
        logger.info("User email: {}", event.getEmail());
        logger.info("User username: {}", event.getName());

        EmailRequest email = new EmailRequest();
        Map<String, Object> variables = Map.of(
            "name", event.getName(),
            "url", appUrl,
            "token", event.getToken()
        );

        email.setTo(event.getEmail());
        email.setTemplateType(EmailTemplateType.USER_REGISTERED);
        email.setTemplateVariables(variables);

        mailService.sendEmail(email);
    }

}
