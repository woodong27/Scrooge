package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeChattingRoom;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeChattingRoomDto {

    private Long id;
    private Integer team;
    private Long roomMemberId;
    private Long challengeId;

    @Builder
    public ChallengeChattingRoomDto(ChallengeChattingRoom challengeChattingRoom) {
        this.id = challengeChattingRoom.getId();
        this.team = challengeChattingRoom.getTeam();
        this.roomMemberId = challengeChattingRoom.getMember().getId();
        this.challengeId = challengeChattingRoom.getChallenge().getId();
    }

}
