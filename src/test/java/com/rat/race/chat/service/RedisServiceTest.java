package com.rat.race.chat.service;

import org.mockito.Mock;
import org.springframework.data.redis.core.RedisTemplate;

class RedisServiceTest {
	@Mock
	private RedisTemplate<String, Object> redisTemplate;

	private RedisPublisher redisService;

//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//		redisService = new RedisPublisher(redisTemplate);
//	}
//
//	@Test
//	void testChat() {
//		ChatMessage chatMessage = new ChatMessage(MessageType.TALK, "1", "user1", "Hello!");
//
//		redisService.chat(chatMessage);
//
//		verify(redisTemplate).convertAndSend("chat:1", chatMessage); // Redis에 발행하는 메시지 검증
//	}
}
