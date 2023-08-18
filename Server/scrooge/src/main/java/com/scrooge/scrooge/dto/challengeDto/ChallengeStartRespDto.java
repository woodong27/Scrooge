package com.scrooge.scrooge.dto.challengeDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChallengeStartRespDto {

    private Double result;
    private String exampleImageAddress;
    private String status;
    private String message;
}
