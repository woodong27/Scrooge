package com.scrooge.scrooge.dto.communityDto;

import com.scrooge.scrooge.domain.community.ArticleBad;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleBadDto {

    private Long id;

    /* 연결 */
    private Long memberId;
    private Long articleId;

    @Builder
    public ArticleBadDto(ArticleBad articleBad) {
        this.id = articleBad.getId();
        this.memberId = articleBad.getMember().getId();
        this.articleId = articleBad.getArticle().getId();
    }

}
