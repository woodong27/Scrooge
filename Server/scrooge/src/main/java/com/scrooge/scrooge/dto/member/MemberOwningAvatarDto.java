package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.domain.member.MemberOwningAvatar;
import com.scrooge.scrooge.dto.AvatarDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberOwningAvatarDto {

    private Long id;
    private AvatarDto avatar;
    private LocalDateTime acquiredAt;

    @Builder
    public MemberOwningAvatarDto(MemberOwningAvatar userOwningAvatar) {
        this.id = userOwningAvatar.getId();
        // user관련 항목을 빼주지 않으면 stackoverflow 에러가 발생
//        this.user = userOwningAvatar.getUser();
        this.avatar = new AvatarDto(userOwningAvatar.getAvatar());
        this.acquiredAt = userOwningAvatar.getAcquiredAt();
    }
}
