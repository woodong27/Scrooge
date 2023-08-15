package com.scrooge.scrooge.dto.paymentHistory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecapDto {

    /* 반환하는 요소
        0. 이 사람 ,, 정산 내역이 있는지 ,,
        1. 많이 쓴 카테고리
        2. 최대 스트릭
        2-1. 최대 스트릭 상위 N% 인지 ,,
        2-1-1. N에 따라 메세지 보내주기
        3. 무슨 시간에 돈을 많이 쓰는 유형인지 ,,, String
     */

    private Boolean hasPaymentHistory;
    private String category;
    private Integer maxStreak;
    private Double topStreakPercentage;
    private String mostSpendTime;

}
