package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.Badge;
import com.scrooge.scrooge.domain.member.MemberOwningBadge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberOwningBadgeDto {

    private Long id;
    private Badge badge;
    private LocalDateTime acquiredAt;

    @Builder
    public MemberOwningBadgeDto(MemberOwningBadge userOwningBadge) {
        this.id = userOwningBadge.getId();
//        this.user = userOwningBadge.getUser();
        this.badge = userOwningBadge.getBadge();
        this.acquiredAt = userOwningBadge.getAcquiredAt();
    }
}
