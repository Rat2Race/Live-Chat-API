package com.rat.race.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rat.race.chat.dto.ChatMessageRequest;
import com.rat.race.chat.dto.ChatMessageResponse;
import com.rat.race.chat.service.RedisMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final RedisMessagePublisher redisMessagePublisher;

    @MessageMapping("/send-message")
    public void sendMessage(@Payload ChatMessageRequest message) {
        try {
            if (message == null || message.roomId() == null || message.message() == null) {
                log.error("Invalid message payload: {}", message);
                throw new IllegalArgumentException("Message payload is invalid");
            }

            messagingTemplate.convertAndSend("/topic/chat/" + message.roomId(), message);
            log.info("Message sent to WebSocket: {}", message);

            String redisMessage = toJson(message);
            redisMessagePublisher.publish("chat-channel", redisMessage);
            log.info("Message published to Redis channel: chat-channel, {}", redisMessage);
        } catch (Exception e) {
            log.error("Failed to send message: {}", e.getMessage(), e);
            throw new RuntimeException("Message sending failed", e);
        }
    }

    private String toJson(ChatMessageRequest message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert message to JSON: {}", e.getMessage(), e);
            throw new RuntimeException("JSON conversion failed", e);
        }
    }
}
