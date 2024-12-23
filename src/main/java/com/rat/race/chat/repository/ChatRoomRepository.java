package com.rat.race.chat.repository;

import com.rat.race.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatRoomRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CHAT_ROOMS = "CHAT_ROOMS";

    public void createChatRoom(String roomName, String hostName) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = new ChatRoom(roomName, hostName);
        redisTemplate.opsForHash().put(CHAT_ROOMS, roomId, chatRoom);
    }

    public String getChatRoomId(String roomName) {
        return redisTemplate.opsForHash().entries(CHAT_ROOMS).entrySet().stream()
                .filter(entry -> ((ChatRoom) entry.getValue()).getRoomName().equals(roomName))
                .map(entry -> (String) entry.getKey())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Failed to get chat room id"));
    }

    public ChatRoom getChatRoom(String chatRoomId) {
        return (ChatRoom) redisTemplate.opsForHash().get(CHAT_ROOMS, chatRoomId);
    }

    public void deleteChatRoom(String chatRoomId) {
        redisTemplate.opsForHash().delete(CHAT_ROOMS, chatRoomId);
    }

    public List<ChatRoom> getAllChatRooms() {
        return redisTemplate.opsForHash().values(CHAT_ROOMS).stream()
                .map(obj -> (ChatRoom) obj)
                .collect(Collectors.toList());
    }
}
