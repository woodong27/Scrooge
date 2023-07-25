package com.scrooge.scrooge.user.dto;

import com.scrooge.scrooge.user.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainPageDTO {
    private String nickname;
    private int level;
    private int exp;
    private Long mainAvtarId;
}
