package com.ricardo.scalable.ecommerce.platform.notifications_service.infrastructure.messaging.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    ConsumerFactory<String, Object> consumerFactory() {
        JsonDeserializer<Object> deserializer = new JsonDeserializer<>();
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*"); // messages can come from any package, it's better to specify packages for securityd
        deserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<String, Object>(
            kafkaProperties.buildConsumerProperties(),
            new StringDeserializer(),
            deserializer
        );
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
                
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
