package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.domain.Badge;
import com.scrooge.scrooge.domain.member.Member;
import lombok.Builder;
import lombok.Data;

@Data
public class ProfileDto {

    private String nickname;
    private String message;
    private Integer level;
    private Avatar mainAvatar;
    private Badge mainBadge;

    @Builder
    public ProfileDto(Member member) {
        this.nickname = member.getNickname();
        this.message = member.getMessage();
        this.level = member.getLevel().getLevel();
        this.mainAvatar = member.getMainAvatar();
        this.mainBadge = member.getMainBadge();
    }
}
