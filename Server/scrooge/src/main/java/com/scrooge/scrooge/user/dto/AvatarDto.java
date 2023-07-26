package com.scrooge.scrooge.user.dto;

import com.scrooge.scrooge.user.domain.Avatar;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AvatarDto {
    private Long id;
    private String name;
    private String img_address;

    @Builder
    public AvatarDto(Avatar avatar) {
        this.id = avatar.getId();
        this.name = avatar.getName();
        this.img_address = avatar.getImg_address();
    }
}
