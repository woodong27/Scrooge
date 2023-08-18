package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeExampleImage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeExampleImageDto {

    private Long id;
    private String imgAddress;

    @Builder
    public ChallengeExampleImageDto(ChallengeExampleImage challengeExampleImage) {
        this.id = challengeExampleImage.getId();
        this.imgAddress = challengeExampleImage.getImgAddress();
    }

}
