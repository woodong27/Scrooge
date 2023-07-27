package com.scrooge.scrooge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOwningBadgeDto {

    private Long id;
    private LocalDateTime acquiredAt;
    private Long userId;
    private Long badgeId;
}
