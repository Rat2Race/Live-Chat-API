package com.rat.race.chat.service;

import com.rat.race.chat.dto.ChatMessage;
import com.rat.race.chat.dto.ChatRoom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ChatService {

    private final MongoService chatMongoService;
    private final RedisPublisher redisPublisher;
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    /**
     * 새로운 채팅방 생성
     */
    public ChatRoom createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = new ChatRoom(roomId, name, 0);
        chatRooms.put(roomId, chatRoom);
        return chatRoom;
    }

    /**
     * 전체 채팅방 조회
     */
    public List<ChatRoom> findAllRooms() {
        return new ArrayList<>(chatRooms.values());
    }

    /**
     * 특정 채팅방 조회
     */
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatMessage sendMessage(ChatMessage message) {
        // MongoDB에 메시지 저장
        ChatMessage savedMessage = chatMongoService.saveMessage(message);

        // RedisPublisher를 통해 Redis로 메시지 발행
        redisPublisher.publish(savedMessage);

        log.info("Message sent and saved to MongoDB: {}", savedMessage);
        return savedMessage;
    }
}
