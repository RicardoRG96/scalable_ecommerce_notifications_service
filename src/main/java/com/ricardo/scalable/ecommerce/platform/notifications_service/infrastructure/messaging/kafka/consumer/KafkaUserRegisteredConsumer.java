package com.ricardo.scalable.ecommerce.platform.notifications_service.infrastructure.messaging.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserRegisteredEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners.UserRegisteredEventListener;

@Component
public class KafkaUserRegisteredConsumer {

    @Autowired
    private final UserRegisteredEventListener eventListener;

    public KafkaUserRegisteredConsumer(UserRegisteredEventListener eventListener) {
        this.eventListener = eventListener;
    }

    @KafkaListener(
        topics = "user-registered", 
        groupId = "user-registered-group", 
        containerFactory = "userRegisteredKafkaListenerContainerFactory"
    )
    public void consume(UserRegisteredEvent event) {
        eventListener.onEvent(event);
    }

}
