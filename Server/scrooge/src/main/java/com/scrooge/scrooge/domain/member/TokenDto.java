package com.scrooge.scrooge.domain.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {

    String accessToken;
    String refreshToken;

}
