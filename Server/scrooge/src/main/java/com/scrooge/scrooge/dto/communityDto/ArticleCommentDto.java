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
    private Long memberId;
    private Long articleId;

    /* USER 정보 */
    private String nickname;
    private String avatarImgAddress;

    @Builder
    public ArticleCommentDto(ArticleComment articleComment) {
        this.id = articleComment.getId();
        this.content = articleComment.getContent();
        this.createdAt = articleComment.getCreatedAt();
        this.memberId = articleComment.getMember().getId();
        this.articleId = articleComment.getArticle().getId();
    }

}
