package com.scrooge.scrooge.dto.communityDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleBadDto {
    /* 연결 */
    private Long memberId;
    private boolean isBad;
}
