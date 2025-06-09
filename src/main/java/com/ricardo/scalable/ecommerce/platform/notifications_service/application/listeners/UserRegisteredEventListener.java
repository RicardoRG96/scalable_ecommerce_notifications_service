package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserRegisteredEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.messaging.EventListener;

@Component
public class UserRegisteredEventListener implements EventListener<UserRegisteredEvent> {

    @Override
    public void onEvent(UserRegisteredEvent event) {
        // Handle the user registered event
        // send welcome email, etc.
        System.out.println("User registered: " + event.getUserId());
    }

}
