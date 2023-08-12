package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeChattingMessage;
import com.scrooge.scrooge.dto.challengeDto.ChallengeChattingMessageReqDto;
import com.scrooge.scrooge.repository.challenge.ChallengeChattingMessageRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeChattingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class ChallengeChattingMessageService {

    private ChallengeChattingMessageRepository messageRepository;
    private ChallengeChattingRoomRepository roomRepository;

    public void saveMessage(ChallengeChattingMessageReqDto messageDto) {
        // DTO로부터 필요한 정보 추출하여 엔티티 생성
        ChallengeChattingMessage message = new ChallengeChattingMessage();
        message.setContent(messageDto.getContent());
        message.setChallengeChattingRoom(roomRepository.findById(messageDto.getChallengeChattingRoomId())
                .orElseThrow(() -> new NotFoundException("해당 챌린지를 찾을 수 없습니다.")));
        // ... 기타 필요한 정보 설정

        // 메시지 저장
        messageRepository.save(message);
    }
}