package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateWeeklyGoalDto {

    private Integer weeklyGoal;

    @Builder
    public UpdateWeeklyGoalDto(Member member) {
        this.weeklyGoal = member.getWeeklyGoal();
    }
}
