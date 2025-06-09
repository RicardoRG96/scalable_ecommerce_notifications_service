package com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners;

import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserBirthdayEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.messaging.EventListener;

@Component
public class UserBirthdayEventListener implements EventListener<UserBirthdayEvent> {

    @Override
    public void onEvent(UserBirthdayEvent event) {
        // Handle the user birthday event
        // send birthday wishes, etc.
        System.out.println("User birthday: " + event.getUserId());
    }

}
