package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.*;
import com.scrooge.scrooge.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;
//    private String name;
    private String nickname;
    private String email;
    private int exp;
    private int streak;
    private int weeklyGoal;
    private int weeklyConsum;
    private LocalDateTime joinedAt;
    private Long levelId;
    private Avatar mainAvatar;
    private Badge mainBadge;

    @Builder
    public MemberDto(Member member) {
        this.id = member.getId();
//        this.name = member.getName();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.exp = member.getExp();
        this.streak = member.getStreak();
        this.weeklyConsum = member.getWeeklyConsum();
        this.weeklyGoal = member.getWeeklyGoal();
        this.joinedAt = member.getJoinedAt();
        this.levelId = member.getLevel().getId();
        this.mainBadge = member.getMainBadge();
        this.mainAvatar = member.getMainAvatar();
    }
//    public MemberDto(Member member) {
//        this.id = member.getId();
//        this.name = member.getName();
//        this.nickname = member.getNickname();
//        this.email = member.getEmail();
//        this.exp = member.getExp();
//        this.streak = member.getStreak();
//        this.weeklyConsum = member.getWeeklyConsum();
//        this.weeklyGoal = member.getWeeklyGoal();
//        this.joinedAt = member.getJoinedAt();
//        this.level = member.getLevel();
//        this.mainBadge = member.getMainBadge();
//        this.mainAvatar = member.getMainAvatar();
//    }
}
