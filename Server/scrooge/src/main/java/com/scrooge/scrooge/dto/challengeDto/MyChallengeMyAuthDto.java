package com.scrooge.scrooge.dto.challengeDto;

import com.scrooge.scrooge.domain.challenge.ChallengeAuth;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MyChallengeMyAuthDto {

    private Integer currentCompletionRate;
    private List<ChallengeAuthDto> challengeAuthList;


}
