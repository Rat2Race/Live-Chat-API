package com.rat.race.chat.controller;

import com.rat.race.chat.dto.ChatMessageRequest;
import com.rat.race.chat.entity.ChatRoom;
import com.rat.race.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/create")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoom request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatRoomService.createChatRoom(request.getRoomName(), request.getHostName()));
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<?> getRoom(@PathVariable String roomName) {
        return ResponseEntity.ok(chatRoomService.getChatRoom(roomName));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable String roomId) {
        chatRoomService.deleteChatRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
