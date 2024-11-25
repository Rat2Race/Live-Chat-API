package com.rat.race.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    private String id;          // 채팅방 고유 ID
    private String name;        // 채팅방 이름
    private int participantCount; // 참가자 수
}
