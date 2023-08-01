package com.scrooge.scrooge.dto.member;

import com.scrooge.scrooge.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePasswordDto {

    private String currentPassword;
    private String newPassword;
}
