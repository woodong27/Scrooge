package com.scrooge.scrooge.dto.communityDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleReviewCountDto {

    private Integer goodCount;
    private Integer badCount;

    @Builder
    public ArticleReviewCountDto(Integer goodCount, Integer badCount) {
        this.goodCount=goodCount;
        this.badCount=badCount;
    }
}
