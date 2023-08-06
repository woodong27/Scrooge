package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ArticleCommentMemberDto {
    private Long id;
    private String nickname;
    private String mainAvatarAddress;

    @Builder
    public ArticleCommentMemberDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.mainAvatarAddress = member.getMainAvatar().getImgAddress();
    }
}
