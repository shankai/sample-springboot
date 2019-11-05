package io.github.shankai.springboot.kafkasample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

/**
 * KafkaConsume
 */
@Service
@Log4j2
public class KafkaConsume {

    @Value("${kafka.topic.sample}")
    private String topic;

    @KafkaListener(topics = "${kafka.topic.sample}")
    public void consume(@Payload String message) {
        log.info("Consume Topic: {}, Message: {}", topic, message);
    }

}