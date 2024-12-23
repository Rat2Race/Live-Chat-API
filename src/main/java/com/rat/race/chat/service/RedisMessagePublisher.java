package com.rat.race.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisMessagePublisher {
    private final RedisTemplate<String, String> redisTemplate;

    public void publish(String topic, String message) {
        try {
            if (topic == null || topic.isEmpty()) {
                throw new IllegalArgumentException("Topic cannot be null or empty");
            }
            if (message == null || message.isEmpty()) {
                throw new IllegalArgumentException("Message cannot be null or empty");
            }
            redisTemplate.convertAndSend(topic, message);
            log.info("Message published to topic [{}]: {}", topic, message);
        } catch (Exception e) {
            log.error("Failed to publish message to topic [{}]: {}", topic, e.getMessage());
            throw new RuntimeException("Redis publish failed", e);
        }
    }
}
