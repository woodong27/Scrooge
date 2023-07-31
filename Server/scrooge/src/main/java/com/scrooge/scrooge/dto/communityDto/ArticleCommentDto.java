package com.scrooge.scrooge.dto.communityDto;

import com.scrooge.scrooge.domain.community.ArticleComment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ArticleCommentDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    /* 연결 */
    private Long userId;
    private Long articleId;

    @Builder
    public ArticleCommentDto(ArticleComment articleComment) {
        this.id = articleComment.getId();
        this.content = articleComment.getContent();
        this.createdAt = articleComment.getCreatedAt();
        this.userId = articleComment.getUser().getId();
        this.articleId = articleComment.getArticle().getId();
    }

}
