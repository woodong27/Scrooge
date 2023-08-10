package com.scrooge.scrooge.service.challenge;

import com.scrooge.scrooge.domain.challenge.ChallengeChattingMessage;
import com.scrooge.scrooge.dto.challengeDto.ChallengeChattingMessageReqDto;

public interface ChallengeChattingMessageService {
    ChallengeChattingMessage createMessage(ChallengeChattingMessageReqDto messageReqDto);
}
