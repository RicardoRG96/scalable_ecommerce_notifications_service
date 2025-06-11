package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserBirthdayEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.messaging.EventListener;

@Component
public class UserBirthdayEventListener implements EventListener<UserBirthdayEvent> {

    private static final Logger logger = LoggerFactory.getLogger(UserBirthdayEventListener.class);

    @Override
    public void onEvent(UserBirthdayEvent event) {
        // Handle the user birthday event
        // send birthday wishes, etc.
        logger.info("User birthday: {}", event.getUserId());
        logger.info("User email: {}", event.getEmail());
        logger.info("User username: {}", event.getName());
        logger.info("User birthday date: {}", event.getBirthday());
    }

}
