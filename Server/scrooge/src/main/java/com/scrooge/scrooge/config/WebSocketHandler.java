package com.scrooge.scrooge.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrooge.scrooge.domain.challenge.ChallengeChattingMessage;
import com.scrooge.scrooge.domain.challenge.ChallengeChattingRoom;
import com.scrooge.scrooge.service.challenge.ChallengeChattingMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChallengeChattingMessageService chattingService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("{}", payload);
        ChallengeChattingMessage chatMessage = objectMapper.readValue(payload, ChallengeChattingMessage.class);
    }
}