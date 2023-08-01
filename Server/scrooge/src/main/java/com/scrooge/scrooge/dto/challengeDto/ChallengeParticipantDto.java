package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeParticipantDto {

    private Long id;
    private Integer team;
    private Boolean dailyCompletion;
    private Integer totalCompletion;

    // 연결
    private Long memberId;
    private Long challengeId;

    @Builder
    public ChallengeParticipantDto(ChallengeParticipant challengeParticipant) {
        this.id = challengeParticipant.getId();
        this.team = challengeParticipant.getTeam();
        this.dailyCompletion = challengeParticipant.getDailyCompletion();
        this.totalCompletion = challengeParticipant.getTotalCompletion();
        this.memberId = challengeParticipant.getMember().getId();
        this.challengeId = challengeParticipant.getChallenge().getId();
    }


}
