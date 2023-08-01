package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeRespDto {

    private Long id;

    private String category;
    private String title;
    private Integer currentParticipants;
    private Integer minParticipants;

    private String mainImgAddress; // 대표 이미지 주소

    @Builder
    public ChallengeRespDto(Challenge challenge) {
        this.id = challenge.getId();
        this.category = challenge.getCategory();
        this.title = challenge.getTitle();
        this.currentParticipants = challenge.getChallengeParticipantList().size();
        this.minParticipants = challenge.getMinParticipants();
        this.mainImgAddress = challenge.getChallengeExampleImageList().get(0).getImgAddress();
    }

}
