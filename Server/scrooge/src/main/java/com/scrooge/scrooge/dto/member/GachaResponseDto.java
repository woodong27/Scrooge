package com.scrooge.scrooge.dto.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GachaResponseDto {

    String avatarId;
    Boolean isDuplicated;

}
