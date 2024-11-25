package com.rat.race.chat.service;

import com.rat.race.chat.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisPublisher {

	private final RedisTemplate<String, Object> redisTemplate;
	private final ChannelTopic topic;

	public void publish(ChatMessage message) {
		redisTemplate.convertAndSend(topic.getTopic(), message);
		log.info("Message published to Redis on topic {}: {}", topic.getTopic(), message);
	}
}
