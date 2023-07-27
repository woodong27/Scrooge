package com.scrooge.scrooge.dto.communityDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private String imgAdress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*연결*/
    private Long userId;
    


}
