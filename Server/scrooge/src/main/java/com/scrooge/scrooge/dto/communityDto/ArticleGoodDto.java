package com.scrooge.scrooge.dto.communityDto;


import com.scrooge.scrooge.domain.community.ArticleGood;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleGoodDto {

    private Long memberId;
    private boolean isGood;
}
