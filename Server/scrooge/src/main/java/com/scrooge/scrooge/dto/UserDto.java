package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private int exp;
    private int streak;
    private int weeklyGoal;
    private int weeklyConsum;
    private LocalDateTime joinedAt;


    @Builder
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.exp = user.getExp();
        this.streak = user.getStreak();
        this.weeklyConsum = user.getWeeklyConsum();
        this.weeklyGoal = user.getWeeklyGoal();
        this.joinedAt = user.getJoinedAt();
    }
}
