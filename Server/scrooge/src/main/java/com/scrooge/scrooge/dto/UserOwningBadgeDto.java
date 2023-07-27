package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.Badge;
import com.scrooge.scrooge.domain.User;
import com.scrooge.scrooge.domain.UserOwningBadge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOwningBadgeDto {

    private Long id;
    private User user;
    private Badge badge;
    private LocalDateTime acquiredAt;

    @Builder
    private UserOwningBadgeDto(UserOwningBadge userOwningBadge) {
        this.id = userOwningBadge.getId();
        this.user = userOwningBadge.getUser();
        this.badge = userOwningBadge.getBadge();
        this.acquiredAt = userOwningBadge.getAcquiredAt();
    }
}
