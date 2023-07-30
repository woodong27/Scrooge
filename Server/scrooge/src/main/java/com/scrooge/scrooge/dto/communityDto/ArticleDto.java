package com.scrooge.scrooge.dto.communityDto;

import com.scrooge.scrooge.domain.community.Article;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String content;
    private String imgAdress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*연결*/
    private Long userId;

    /* 글 전체 조회를 위한 필드 */
    private String avatarImgAddress;
    private String nickname;


    @Builder
    public ArticleDto(Article article) {
        this.id = article.getId();
        this.content = article.getContent();
        this.imgAdress = article.getImgAdress();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.userId = article.getUser().getId();
    }

}
