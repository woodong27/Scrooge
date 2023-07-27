package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.Avatar;
import com.scrooge.scrooge.domain.Badge;
import com.scrooge.scrooge.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPageDto {
    private String nickname;
    private int exp;
    private int weeklyGoal;
    private int weeklyConsum;
    private Badge mainBadge;
    private Avatar mainAvatar;
    private Level level;
}
