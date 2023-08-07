package com.scrooge.scrooge.dto.communityDto;

import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.member.ArticleMemberDto;
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

    /*연결*/
    private Long memberId;
    private String memberNickname;
    private String memberAvatarAddress;


    @Builder
    public ArticleDto(Article article) {
        this.id = article.getId();
        this.content = article.getContent();
        this.imgAdress = article.getImgAdress();
        this.createdAt = article.getCreatedAt();
        this.memberId = article.getMember().getId();
        this.memberNickname = article.getMember().getNickname();
        this.memberAvatarAddress = article.getMember().getMainAvatar().getImgAddress();
    }

}
