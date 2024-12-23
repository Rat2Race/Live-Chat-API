package com.rat.race.chat.dto;

public record ChatMessageRequest(
        String roomId,
        String sender,
        String message
) {
}
