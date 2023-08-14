package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeParticipant;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TeamMemberAuths {
    private Long memberId;
    private Integer team;
    private List<TeamMemberAuth> memberAuthList;

    @Builder
    public TeamMemberAuths(ChallengeParticipant challengeParticipant) {
        this.memberId = challengeParticipant.getMember().getId();
        this.team = challengeParticipant.getTeam();
        this.memberAuthList = challengeParticipant.getChallengeAuths().stream()
                .map(TeamMemberAuth::new)
                .collect(Collectors.toList());
    }
}
