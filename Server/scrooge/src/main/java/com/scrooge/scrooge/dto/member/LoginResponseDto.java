package com.scrooge.scrooge.dto.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDto {

    private Long memberId;
    private String token;

    /* 리프레시 토큰 */
    private String refreshToken;
}
