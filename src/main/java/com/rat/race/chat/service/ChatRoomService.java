package com.rat.race.chat.service;

import com.rat.race.chat.entity.ChatRoom;
import com.rat.race.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public String createChatRoom(String roomName, String hostName) {
        if (roomName == null || roomName.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be null or empty");
        }
        if (hostName == null || hostName.trim().isEmpty()) {
            throw new IllegalArgumentException("Host name cannot be null or empty");
        }

        log.info("Creating chat room with name: {} and host: {}", roomName, hostName);
        chatRoomRepository.createChatRoom(roomName, hostName);

        if (chatRoomRepository.getChatRoom(roomName) == null) {
            throw new IllegalArgumentException("Failed to create chat room");
        }

        log.info("Chat room created successfully: {}", roomName);
        return roomName;
    }

    public ChatRoom getChatRoom(String roomName) {
        if (roomName == null || roomName.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be null or empty");
        }

        log.info("Fetching chat room with name: {}", roomName);
        String chatRoomId = chatRoomRepository.getChatRoomId(roomName);
        ChatRoom chatRoom = chatRoomRepository.getChatRoom(chatRoomId);

        if (chatRoom == null) {
            throw new IllegalArgumentException("Chat room not found with name: " + roomName);
        }

        log.info("Chat room fetched successfully: {}", roomName);
        return chatRoom;
    }

    public boolean deleteChatRoom(String roomId) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Room ID cannot be null or empty");
        }

        log.info("Deleting chat room with ID: {}", roomId);
        chatRoomRepository.deleteChatRoom(roomId);

        if (chatRoomRepository.getChatRoom(roomId) != null) {
            throw new IllegalStateException("Failed to delete chat room with ID: " + roomId);
        }

        log.info("Chat room deleted successfully: {}", roomId);
        return true;
    }
}
