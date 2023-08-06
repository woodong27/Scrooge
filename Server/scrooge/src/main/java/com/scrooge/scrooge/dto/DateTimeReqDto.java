package com.scrooge.scrooge.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DateTimeReqDto {

    String date;

    @Builder
    public DateTimeReqDto(String date) {
        this.date = date;
    }

}
