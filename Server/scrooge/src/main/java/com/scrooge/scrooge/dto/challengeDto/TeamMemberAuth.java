package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import lombok.Builder;
import lombok.Data;

@Data
public class TeamMemberAuth {
    private boolean isSuccess;
    private String authImageAddress;

    @Builder
    public TeamMemberAuth(ChallengeAuth challengeAuth){
        this.isSuccess = challengeAuth.getIsSuccess();
        this.authImageAddress = challengeAuth.getImgAddress();
    }
}
