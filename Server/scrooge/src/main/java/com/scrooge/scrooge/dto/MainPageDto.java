package com.scrooge.scrooge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPageDto {
    private String nickname;
    private Long levelId;
    private int exp;
    private Long mainAvatarId;
}
