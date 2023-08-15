package com.scrooge.scrooge.controller.challenge;

import com.scrooge.scrooge.config.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat/{challengeId}/{team}") // 클라이언트에서 메시지를 보낼 때의 엔드포인트
    @SendTo("/topic/chat/{challengeId}/{team}") // 메시지를 구독 중인 클라이언트에게 브로드캐스팅
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
