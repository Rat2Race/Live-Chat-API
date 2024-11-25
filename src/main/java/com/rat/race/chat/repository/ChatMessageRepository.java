package com.rat.race.chat.repository;

import com.rat.race.chat.dto.ChatMessage;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomId(String roomId);
}
