package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.Challenge;
import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ChallengeRespDto {

    private Long id;

    private String category;
    private String title;
    private Integer currentParticipants;
    private Integer minParticipants;
    private Integer status;

    private String mainImgAddress; // 대표 이미지 주소

    /* 상세화면 위한 요소 */
    private String authMethod;
    private List<ChallengeExampleImageDto> challengeExampleImageList;
    private String description;
    private String period;

    @Builder
    public ChallengeRespDto(Challenge challenge) {
        this.id = challenge.getId();
        this.category = challenge.getCategory();
        this.title = challenge.getTitle();
        this.currentParticipants = challenge.getChallengeParticipantList().size();
        this.minParticipants = challenge.getMinParticipants();
        this.status = challenge.getStatus();

//        this.mainImgAddress = challenge.getChallengeExampleImageList().get(0).getImgAddress();

        this.authMethod = challenge.getAuthMethod();
        this.challengeExampleImageList = challenge.getChallengeExampleImageList()
                .stream()
                .map(ChallengeExampleImageDto::new)
                .collect(Collectors.toList());
        this.description = challenge.getDescription();
        this.period = challenge.getPeriod();
    }


}
