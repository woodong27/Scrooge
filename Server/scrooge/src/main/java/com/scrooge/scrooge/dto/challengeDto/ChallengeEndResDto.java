package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeEndResDto {

    private Long id;
    private String category;
    private String title;
    private Integer status; // 당연히 3으로 ,,,
    private Integer currentParticipants;
    private Integer minParticipants;
    private String period;
    private String mainImageAddress;

    // 이겼는지 졌는지 ,,
    private Boolean isWin;

    @Builder
    public ChallengeEndResDto(Challenge challenge, Boolean isWin) {
        this.id = challenge.getId();
        this.category = challenge.getCategory();
        this.title = challenge.getTitle();
        this.status = challenge.getStatus();
        this.currentParticipants = challenge.getChallengeParticipantList().size();
        this.minParticipants = challenge.getMinParticipants();
        this.mainImageAddress = challenge.getChallengeExampleImageList().get(0).getImgAddress();
        this.period = challenge.getPeriod();
        this.isWin = isWin;
    }

}
