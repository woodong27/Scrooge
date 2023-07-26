package com.scrooge.scrooge.user.dto;

import com.scrooge.scrooge.user.domain.Level;
import lombok.*;

@Data // 모든 필드에 대한 getter와 setter 메서드 생성
@NoArgsConstructor
public class LevelDto {

    private Long id;
    private int level;
    private int required_exp;
    private int gacha;

    @Builder
    public LevelDto(Level level) {
        this.id = level.getId();
        this.level = level.getLevel();
        this.required_exp = level.getRequired_exp();
        this.gacha = level.getGacha();
    }

}
