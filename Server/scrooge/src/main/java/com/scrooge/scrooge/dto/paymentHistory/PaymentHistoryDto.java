package com.scrooge.scrooge.dto.paymentHistory;

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
    private Boolean isSettled; // 정산 완료 여부

    /*연결*/
    private Long memberId;

    @Builder
    public PaymentHistoryDto(PaymentHistory paymentHistory) {
        this.id = paymentHistory.getId();
        this.paidAt = paymentHistory.getPaidAt();
        this.category = paymentHistory.getCategory();
        this.amount = paymentHistory.getAmount();
        this.usedAt = paymentHistory.getUsedAt();
        this.cardName = paymentHistory.getCardName();

        this.isSettled = paymentHistory.getIsSettled();

        this.memberId = paymentHistory.getMember().getId();
    }
}
