package com.scrooge.scrooge.dto.communityDto;

import com.scrooge.scrooge.domain.community.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* 연결 */
    private Long userId;
    private Long articleId;

    @Builder
    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.userId = comment.getUser().getId();
        this.articleId = comment.getArticle().getId();
    }

}
