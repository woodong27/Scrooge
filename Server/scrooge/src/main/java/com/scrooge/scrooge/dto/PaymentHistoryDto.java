package com.scrooge.scrooge.dto;

import com.scrooge.scrooge.domain.PaymentHistory;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor //인자 없이 객체 생성 가능
public class PaymentHistoryDto {
    private Long id;
    private LocalDateTime paidAt;
    private String category;
    private Integer amount;
    private String usedAt;
    private String cardName;

    /*연결*/
    private Long userId;

    @Builder
    public PaymentHistoryDto(PaymentHistory paymentHistory) {
        this.id = paymentHistory.getId();
        this.paidAt = paymentHistory.getPaidAt();
        this.category = paymentHistory.getCategory();
        this.amount = paymentHistory.getAmount();
        this.usedAt = paymentHistory.getUsedAt();
        this.cardName = paymentHistory.getCardName();
        this.userId = paymentHistory.getUser().getId();
    }
}
