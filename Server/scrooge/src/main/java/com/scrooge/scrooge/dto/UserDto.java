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
    private int weekly_goal;
    private int weekly_consum;
    private LocalDateTime joined_at;

    @Builder
    public UserDto(User user) {
        this.id = user.getUserId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.exp = user.getExp();
        this.streak = user.getStreak();
        this.weekly_consum = user.getWeekly_consum();
        this.joined_at = user.getJoined_at();
    }
}
