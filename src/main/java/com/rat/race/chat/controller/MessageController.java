package com.rat.race.chat.controller;

import com.rat.race.chat.dto.ChatMessageRequest;
import com.rat.race.chat.dto.ChatMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageController {

    @MessageMapping("/chat.{chatRoomId}")
    @SendTo("/topic/chat.{chatRoomId}")
    public ChatMessageResponse sendMessage(ChatMessageRequest request, @DestinationVariable Long chatRoomId) {
        return new ChatMessageResponse(request.username(), request.content());
    }

    @MessageExceptionHandler
    public void handleException(RuntimeException e) {
        log.info("Exception: {}", e.getMessage());
    }
}
