package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MyChallengeRespDto {

    private Long id;
    private String mainImgAddress;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String period;
    private String title;

    private Integer teamZeroSuccessCount;
    private Integer teamOneSuccessCount;

    @Builder
    public MyChallengeRespDto(Challenge challenge) {
        this.id = challenge.getId();
        this.mainImgAddress = challenge.getChallengeExampleImageList().get(0).getImgAddress();
        this.startDate = challenge.getStartDate();
        this.endDate = challenge.getEndDate();
        this.period = challenge.getPeriod();
        this.title = challenge.getTitle();
    }
}
