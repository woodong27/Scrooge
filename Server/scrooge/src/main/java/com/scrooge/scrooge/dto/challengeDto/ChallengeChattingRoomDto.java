package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeChattingRoom;
import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
// 채팅방 정보를 반환할 떄 써야 할 DTO
public class ChallengeChattingRoomDto {

    private Long id;
    private Integer team;
    private List<Long> roomParticipantIds;
    private Long challengeId;

    public ChallengeChattingRoomDto(ChallengeChattingRoom challengeChattingRoom) {
        this.id = challengeChattingRoom.getId();
        this.team = challengeChattingRoom.getTeam();
        this.roomParticipantIds = challengeChattingRoom.getRoomParticipantIds();
        this.challengeId = challengeChattingRoom.getChallenge().getId();
    }

}
