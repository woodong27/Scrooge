package com.scrooge.scrooge.dto.communityDto;


import com.scrooge.scrooge.domain.community.ArticleGood;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleGoodDto {

    private Long id;

    /* 연결 */
    private Long userId;
    private Long articleId;

    @Builder
    public ArticleGoodDto(ArticleGood articleGood) {
        this.id = articleGood.getId();
        this.userId = articleGood.getUser().getId();
        this.articleId = articleGood.getArticle().getId();
    }

}
