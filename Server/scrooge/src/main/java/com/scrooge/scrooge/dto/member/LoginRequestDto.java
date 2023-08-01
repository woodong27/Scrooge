package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {

    private String email;
    private String password;

    @Builder
    private LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
