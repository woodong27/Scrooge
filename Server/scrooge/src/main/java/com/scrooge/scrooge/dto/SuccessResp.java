package com.scrooge.scrooge.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuccessResp {
    private int success;

    @Builder
    public SuccessResp(int success){
        this.success = success;
    }
}