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
    private ArticleCommentMemberDto articleCommentMember;


    @Builder
    public ArticleCommentDto(ArticleComment articleComment) {
        this.id = articleComment.getId();
        this.content = articleComment.getContent();
        this.createdAt = articleComment.getCreatedAt();
        this.articleCommentMember = new ArticleCommentMemberDto(articleComment.getMember());
    }

}
