package com.scrooge.scrooge.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadDto {
    private MultipartFile image; //!!!!!!!!!이미지 업로드 관련 부분
}
