package com.scrooge.scrooge.dto.communityDto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleGoodDto {

    private Long memberId;
    private boolean isGood;
}
