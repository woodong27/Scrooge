package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChallengeAuthDto {

    private Long id;
    private LocalDateTime createdAt;
    private String imageAddress;
    private Boolean isSuccess;

    /* 연결 */
    private Long challengeParticipantId;

    @Builder
    public ChallengeAuthDto(ChallengeAuth challengeAuth) {
        this.id = challengeAuth.getId();
        this.createdAt = challengeAuth.getCreatedAt();
        this.imageAddress = challengeAuth.getImgAddress();
        this.isSuccess = challengeAuth.getIsSuccess();
        this.challengeParticipantId = challengeAuth.getChallengeParticipant().getId();
    }

}
