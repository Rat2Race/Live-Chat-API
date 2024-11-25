package com.morak.morak.chat.controller;

import com.morak.morak.chat.dto.ChatMessage;
import com.morak.morak.chat.dto.ChatRoom;
import com.morak.morak.chat.service.ChatService;
import com.morak.morak.chat.service.MongoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final MongoService chatMongoService;
    private final ChatService chatService;

    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */

    @MessageMapping("/chat/message")
    public ResponseEntity<ChatMessage> message(ChatMessage message) {
        ChatMessage chatMessage = chatMongoService.saveMessage(message);
        chatService.sendMessage(chatMessage); //RedisPublisher 호출
        return ResponseEntity.status(HttpStatus.OK).body(chatMessage);
    }
    /**
     * 새로운 채팅방 생성
     */
    @PostMapping("/room")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestParam String roomName) {
        ChatRoom chatRoom = chatService.createRoom(roomName);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatRoom);
    }

    /**
     * 특정 채팅방의 메시지 목록 조회
     */
    @GetMapping("/room/{roomId}/messages")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable String roomId) {
        List<ChatMessage> messages = chatMongoService.getMessagesByRoomId(roomId);
        return ResponseEntity.ok(messages);
    }

    /**
     * 전체 채팅방 목록 조회
     */
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> getAllRooms() {
        List<ChatRoom> chatRooms = chatService.findAllRooms();
        return ResponseEntity.ok(chatRooms);
    }

    /**
     * 특정 채팅방 정보 조회
     */
    @GetMapping("/room/{roomId}")
    public ResponseEntity<ChatRoom> getRoomInfo(@PathVariable String roomId) {
        ChatRoom chatRoom = chatService.findRoomById(roomId);
        return ResponseEntity.ok(chatRoom);
    }
}