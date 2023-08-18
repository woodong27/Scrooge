package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeChattingMessage;
import com.scrooge.scrooge.dto.challengeDto.ChallengeChattingRoomDto;
import com.scrooge.scrooge.dto.member.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ChallengeChattingRoomService {
    private ChallengeChattingRoomDto chattingRoomDto;
    private MemberDto memberDto;
    private Set<WebSocketSession> sessions = new HashSet<>();
    public void handlerActions(WebSocketSession session, ChallengeChattingMessage chattingMessage, ChallengeChattingMessageService chattingService) {
        sendMessage(chattingMessage, chattingService);
    }

    private <T> void sendMessage(T message, ChallengeChattingMessageService chattingMessageService) {
        TextMessage textMessage = new TextMessage(message.toString());

        sessions.parallelStream().forEach(session -> {
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                // Handle the exception appropriately
                e.printStackTrace();
            }
        });
    }
}
