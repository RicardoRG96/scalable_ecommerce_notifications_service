package com.ricardo.scalable.ecommerce.platform.notifications_service.infrastructure.messaging.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ricardo.scalable.ecommerce.platform.libs_common.events.UserBirthdayEvent;

@Configuration
public class KafkaUserBirthdayConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaUserBirthdayConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    ConsumerFactory<String, UserBirthdayEvent> userBirthdayConsumerFactory() {
        JsonDeserializer<UserBirthdayEvent> deserializer = new JsonDeserializer<>(UserBirthdayEvent.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, UserBirthdayEvent> userBirthdayKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserBirthdayEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userBirthdayConsumerFactory());
        return factory;
    }

}
