package com.scrooge.scrooge.dto.paymentHistory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecapDto {

    private Boolean hasLastMonthPaymentHistory; // 저번 달 소비 내역 있는 지,,,
    private Integer lastMonthTotal; // 저번 달 소비내역 총합
    private Integer thisMonthTotal; // 이번 달 소비내역 총합
    private Boolean didSpendMoreThisMonth; // 이번달에 돈 더씀??
    private Boolean hasPaymentHistory;
    private String category;
    private Integer maxStreak;
    private Double topStreakPercentage;
    private String mostSpendTime;

}
