package com.rat.race.gpt.dto;

import org.springframework.ai.chat.ChatResponse;

public record GptResponseDto(
        String response
) {
    public static GptResponseDto fromChatResponse(ChatResponse chatResponse) {
        return new GptResponseDto(chatResponse.getResult().getOutput().getContent());
    }
}
