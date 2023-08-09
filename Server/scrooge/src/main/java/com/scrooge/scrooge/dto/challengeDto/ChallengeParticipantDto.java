package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeParticipantDto {

    private Long memberId;
    private Long challengeId;
    private Integer team;
    private Integer currentParticipants;

    @Builder
    public ChallengeParticipantDto(ChallengeParticipant challengeParticipant) {

        this.team = challengeParticipant.getTeam();
        this.memberId = challengeParticipant.getMember().getId();
        this.challengeId = challengeParticipant.getChallenge().getId();
        this.currentParticipants = challengeParticipant.getChallenge().getChallengeParticipantList().size();
    }


}
