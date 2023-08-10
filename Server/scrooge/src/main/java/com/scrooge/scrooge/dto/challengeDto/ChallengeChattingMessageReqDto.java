package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeChattingMessage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChallengeChattingMessageReqDto {

    /* 프론트에서 채팅 메시지 데이터를 서버에 보내줄 때 사용하는 DTO */

    private Long id;
    private String content;
    private LocalDateTime createdAt;

    // 연결
    private Long challengeChattingRoomId;
    private Long messageAuthorId;

    @Builder
    public ChallengeChattingMessageReqDto(ChallengeChattingMessage challengeChattingMessage) {
        this.id = challengeChattingMessage.getId();
        this.content = challengeChattingMessage.getContent();
        this.createdAt = challengeChattingMessage.getCreatedAt();
        this.challengeChattingRoomId = challengeChattingMessage.getChallengeChattingRoom().getId();
        this.messageAuthorId = challengeChattingMessage.getMember().getId();
    }

}
