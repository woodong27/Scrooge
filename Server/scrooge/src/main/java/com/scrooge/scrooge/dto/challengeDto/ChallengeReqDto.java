package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChallengeReqDto {

    private Long id;
    private String title;
    private String category;
    private Integer minParticipants;
    private Integer maxParticipants;
    private String authMethod;
    private String period;
    private Integer totalAuthCount;
    private String description;
    private Integer status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /* 연결 */
    private Long challengeMasterId;

    @Builder
    public ChallengeReqDto(Challenge challenge) {
        this.id = challenge.getId();
        this.title = challenge.getTitle();
        this.category = challenge.getCategory();
        this.minParticipants = challenge.getMinParticipants();
        this.maxParticipants = 20;
        this.authMethod = challenge.getAuthMethod();
        this.period = challenge.getPeriod();
        this.totalAuthCount = challenge.getTotalAuthCount();
        this.description = challenge.getDescription();
        this.status = challenge.getStatus();
        this.startDate = challenge.getStartDate();
        this.endDate = challenge.getEndDate();
        this.challengeMasterId = challenge.getChallengeMaster().getId();
    }

}
