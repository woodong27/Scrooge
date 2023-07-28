package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.Badge;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadgeDto {

    private Long id;
    private String badgeName;
    private String badgeDescription;
    private String imgAddress;

    @Builder
    public BadgeDto(Badge badge) {
        this.id = badge.getId();
        this.badgeName = badge.getBadgeName();
        this.badgeDescription = badge.getBadgeDescription();
        this.imgAddress = badge.getImgAddress();
    }
}
