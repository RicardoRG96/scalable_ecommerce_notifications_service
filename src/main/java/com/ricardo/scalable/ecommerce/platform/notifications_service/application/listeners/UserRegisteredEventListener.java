package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserRegisteredEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.messaging.EventListener;

@Component
public class UserRegisteredEventListener implements EventListener<UserRegisteredEvent> {

    private static final Logger logger = LoggerFactory.getLogger(UserRegisteredEventListener.class);

    @Override
    public void onEvent(UserRegisteredEvent event) {
        // Handle the user registered event
        // send welcome email, etc.
        logger.info("User registered: {}", event.getUserId());
        logger.info("User email: {}", event.getEmail());
        logger.info("User username: {}", event.getName());
    }

}
