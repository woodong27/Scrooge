package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private int exp;
    private int streak;
    private int weeklyGoal;
    private int weeklyConsum;
    private LocalDateTime joinedAt;
    private Level level;
    private Avatar mainAvatar;
    private Badge mainBadge;
    private List<UserOwningAvatarDto> userOwningAvatars;
    private List<UserOwningBadgeDto> userOwningBadges;
    private List<UserSelectedQuestDto> userSelectedQuests;

//    @Builder
//    public UserDto(User user) {
//        this.id = user.getId();
//        this.name = user.getName();
//        this.nickname = user.getNickname();
//        this.email = user.getEmail();
//        this.exp = user.getExp();
//        this.streak = user.getStreak();
//        this.weeklyConsum = user.getWeeklyConsum();
//        this.weeklyGoal = user.getWeeklyGoal();
//        this.joinedAt = user.getJoinedAt();
//        this.level = user.getLevel();
//        this.mainBadge = user.getMainBadge();
//        this.mainAvatar = user.getMainAvatar();
//        this.userOwningAvatars = user.getUserOwningAvatars()
//    }
}
