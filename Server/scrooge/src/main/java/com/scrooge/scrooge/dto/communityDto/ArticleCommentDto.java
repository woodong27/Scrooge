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
    private Long memberId;
    private String memberNickname;
    private String memberAvatarAddress;


    @Builder
    public ArticleCommentDto(ArticleComment articleComment) {
        this.id = articleComment.getId();
        this.content = articleComment.getContent();
        this.memberId = articleComment.getMember().getId();
        this.memberNickname = articleComment.getMember().getNickname();
        this.memberAvatarAddress = articleComment.getMember().getMainAvatar().getId().toString();
    }

}
