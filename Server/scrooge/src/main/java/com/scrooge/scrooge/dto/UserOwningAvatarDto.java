package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.UserOwningAvatar;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserOwningAvatarDto {

    private Long id;
    private LocalDateTime acquiredAt;
//    private Boolean is_main_avatar;
    private Long userId; // 연결된 userId
    private Long avatarId; // 연결된 avatarId

    @Builder
    public UserOwningAvatarDto(UserOwningAvatar userOwningAvatar) {
        this.id = userOwningAvatar.getId();
        this.acquiredAt = userOwningAvatar.getAcquiredAt();
//        this.is_main_avatar = userOwningAvatar.getIsMainAvatar();//다시 확인
        this.userId = userOwningAvatar.getUser().getId();
        this.avatarId = userOwningAvatar.getAvatar().getId();
    }

}
