package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ChallengeDetailDto {
    private Long id;
    private String title;
    private String category;
    private String description;
    private String authMethod;
    private Integer minParticipants;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String period;
    private Integer status;
    private Long masterId;
    private String mainImageAddress;
    private List<ChallengeExampleImageDto> challengeExampleImageDtoList;

    @Builder
    public ChallengeDetailDto(Challenge challenge, List<ChallengeExampleImage> challengeExampleImages) {
        this.id = challenge.getId();
        this.title = challenge.getTitle();
        this.category = challenge.getCategory();
        this.description = challenge.getDescription();
        this.authMethod = challenge.getAuthMethod();
        this.minParticipants = challenge.getMinParticipants();
        this.maxParticipants = challenge.getMaxParticipants();
        this.currentParticipants = challenge.getChallengeParticipantList().size();
        this.startDate = challenge.getStartDate();
        this.endDate = challenge.getEndDate();
        this.period = challenge.getPeriod();
        this.status = challenge.getStatus();
        this.masterId = challenge.getChallengeMaster().getId();
        this.mainImageAddress = challengeExampleImages.get(0).getImgAddress();
        this.challengeExampleImageDtoList = challengeExampleImages.stream().map(ChallengeExampleImageDto::new).collect(Collectors.toList());
    }
}
