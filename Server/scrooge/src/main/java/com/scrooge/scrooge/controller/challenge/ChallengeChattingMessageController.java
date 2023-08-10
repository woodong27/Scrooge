package com.scrooge.scrooge.controller.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeChattingMessage;
import com.scrooge.scrooge.dto.challengeDto.ChallengeChattingMessageReqDto;
import com.scrooge.scrooge.service.challenge.ChallengeChattingMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChallengeChattingMessageController {

    private final ChallengeChattingMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate; // SimpMessagingTemplate 주입

    public ChallengeChattingMessageController(ChallengeChattingMessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload ChallengeChattingMessageReqDto messageReqDto) {
        // 메시지 생성 및 저장 로직
        ChallengeChattingMessage createdMessage = messageService.createMessage(messageReqDto);

        // 받은 메시지를 다시 클라이언트로 전송
        messagingTemplate.convertAndSend("/topic/chat/" + messageReqDto.getChallengeChattingRoomId(), createdMessage);
    }
}
