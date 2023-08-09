package com.scrooge.scrooge.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDto {

    private Long memberId;
    private String token;
}
