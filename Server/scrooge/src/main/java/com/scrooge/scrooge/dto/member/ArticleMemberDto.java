package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleMemberDto {
    private Long id;
    private String nickname;
    private String mainAvatarAddress;
    private String mainBadgeAddress;

    @Builder
    public ArticleMemberDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.mainAvatarAddress = member.getMainAvatar().getId().toString();
        this.mainBadgeAddress = member.getMainBadge().getImgAddress();
    }
}

