package com.scrooge.scrooge.controller.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeChattingMessage;
import com.scrooge.scrooge.dto.challengeDto.ChallengeChattingMessageReqDto;
import com.scrooge.scrooge.service.challenge.ChallengeChattingMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChallengeChattingMessageController {

    @Autowired
    private ChallengeChattingMessageService messageService;

    @MessageMapping("/message")
    @SendTo("/chatroom/{roomid}")
    public ChallengeChattingMessageReqDto receiveMessage(@Payload ChallengeChattingMessageReqDto messageDto) {
        // 받은 메시지를 서비스 레이어를 통해 처리
        messageService.saveMessage(messageDto);

        // 클라이언트에게 다시 보내지 않고, 저장된 메시지 정보를 리턴
        return messageDto;
    }
}
