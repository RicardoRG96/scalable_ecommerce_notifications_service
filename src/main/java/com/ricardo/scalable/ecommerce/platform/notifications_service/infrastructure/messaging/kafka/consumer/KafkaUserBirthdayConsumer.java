package com.ricardo.scalable.ecommerce.platform.notifications_service.infrastructure.messaging.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserBirthdayEvent;
import com.ricardo.scalable.ecommerce.platform.notifications_service.application.listeners.UserBirthdayEventListener;

@Component
public class KafkaUserBirthdayConsumer {

    @Autowired
    private final UserBirthdayEventListener eventListener;

    public KafkaUserBirthdayConsumer(UserBirthdayEventListener eventListener) {
        this.eventListener = eventListener;
    }

    @KafkaListener(topics = "user-birthday", groupId = "notifications-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Object> record) {
        if (record.value() instanceof UserBirthdayEvent event) {
            eventListener.onEvent(event);
        }
    }

}
