package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StartedChallengeDto {

    private Long id;
    private String title;
    private String category;
    private String description;
    private String authMethod;
    private Integer minParticipants;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private List<Long> participantIds;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String period;
    private Integer status;
    private Long masterId;
    private Integer teamZeroAuthCount;
    private Integer teamOneAuthCount;
    private String mainImageAddress;
    private List<ChallengeExampleImageDto> challengeExampleImageDtoList;

    @Builder
    public StartedChallengeDto(Challenge challenge, List<ChallengeExampleImage> challengeExampleImages,
                               Integer teamZeroAuthCount, Integer teamOneAuthCount) {
        this.id = challenge.getId();
        this.title = challenge.getTitle();
        this.category = challenge.getCategory();
        this.description = challenge.getDescription();
        this.authMethod = challenge.getAuthMethod();
        this.minParticipants = challenge.getMinParticipants();
        this.maxParticipants = challenge.getMaxParticipants();
        this.currentParticipants = challenge.getChallengeParticipantList().size();
        this.participantIds = challenge.getChallengeParticipantList().stream()
                .map(challengeParticipant -> challengeParticipant.getMember().getId())
                .collect(Collectors.toList());
        this.startDate = challenge.getStartDate();
        this.endDate = challenge.getEndDate();
        this.period = challenge.getPeriod();
        this.status = challenge.getStatus();
        this.masterId = challenge.getChallengeMaster().getId();
        this.teamZeroAuthCount = teamZeroAuthCount;
        this.teamOneAuthCount = teamOneAuthCount;
        this.mainImageAddress = challengeExampleImages.get(0).getImgAddress();
        this.challengeExampleImageDtoList = challengeExampleImages.stream().map(ChallengeExampleImageDto::new).collect(Collectors.toList());
    }
}
