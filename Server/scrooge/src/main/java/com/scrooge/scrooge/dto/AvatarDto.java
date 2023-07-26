package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.Avatar;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AvatarDto {
    private Long id;
    private String name;
    private String imgAddress;

    @Builder
    public AvatarDto(Avatar avatar) {
        this.id = avatar.getId();
        this.name = avatar.getName();
        this.imgAddress = avatar.getImgAddress();
    }
}
