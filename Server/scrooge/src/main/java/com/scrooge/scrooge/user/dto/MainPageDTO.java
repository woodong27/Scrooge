package com.scrooge.scrooge.user.dto;

import com.scrooge.scrooge.user.domain.Level;
import lombok.Setter;

@Setter
public class MainPageDTO {
    private String nickname;
    private Level level;
    private int exp;
    private Long mainAvtarId;
}
