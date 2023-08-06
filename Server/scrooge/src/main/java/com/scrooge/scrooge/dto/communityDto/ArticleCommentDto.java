package com.scrooge.scrooge.dto.communityDto;

import com.scrooge.scrooge.domain.community.ArticleComment;
import com.scrooge.scrooge.dto.member.ArticleCommentMemberDto;
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
    private ArticleCommentMemberDto articleCommentMember;
    private Long articleId;

//    /* USER 정보 */
//    private String nickname;
//    private String avatarImgAddress;

    @Builder
    public ArticleCommentDto(ArticleComment articleComment) {
        this.id = articleComment.getId();
        this.content = articleComment.getContent();
        this.createdAt = articleComment.getCreatedAt();
        this.articleCommentMember = new ArticleCommentMemberDto(articleComment.getMember());
        this.articleId = articleComment.getArticle().getId();
    }

}
