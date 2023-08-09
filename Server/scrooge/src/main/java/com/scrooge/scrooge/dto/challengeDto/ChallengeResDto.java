package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeResDto {
    private Long id;
    private String category;
    private String title;
    private Integer currentParticipants;
    private Integer minParticipants;
    private String mainImageAddress;

    @Builder
    public ChallengeResDto(Challenge challenge) {
        this.id = challenge.getId();
        this.category = challenge.getCategory();
        this.title = challenge.getTitle();
        this.currentParticipants = challenge.getChallengeParticipantList().size();
        this.minParticipants = challenge.getMinParticipants();
        this.mainImageAddress = challenge.getChallengeExampleImageList().get(0).getImgAddress();
    }
}
