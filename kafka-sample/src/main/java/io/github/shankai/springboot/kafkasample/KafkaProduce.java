package io.github.shankai.springboot.kafkasample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

/**
 * KafkaProduce
 */
@Service
@Log4j2
public class KafkaProduce {

    @Value("${kafka.topic.sample}")
    private String topic;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void send(String data) {
        log.info("Send to {}, Message is {}", topic, data);
        kafkaTemplate.send(topic, data);
    }
}