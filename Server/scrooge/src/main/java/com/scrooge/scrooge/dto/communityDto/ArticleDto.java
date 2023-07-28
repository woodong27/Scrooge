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
    private String title;
    private String content;
    private String imgAdress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*연결*/
    private Long memberId;

    @Builder
    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.imgAdress = article.getImgAdress();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }

}
