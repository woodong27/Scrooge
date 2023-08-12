package com.scrooge.scrooge.dto.challengeDto;

import lombok.Builder;
import lombok.Data;

@Data
public class ImagePaths {
    private String imageURL1;
    private String imageURL2;

    @Builder
    public ImagePaths(String url1, String url2) {
        this.imageURL1 = url1;
        this.imageURL2 = url2;
    }
}
