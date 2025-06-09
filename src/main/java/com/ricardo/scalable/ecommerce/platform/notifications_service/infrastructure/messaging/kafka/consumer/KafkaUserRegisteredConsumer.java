package com.ricardo.scalable.ecommerce.platform.notifications_service.infrastructure.messaging.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
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

    @KafkaListener(topics = "user-registered", groupId = "notifications-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Object> record) {
        if (record.value() instanceof UserRegisteredEvent event) {
            eventListener.onEvent(event);
        }
    }

}
