package com.scrooge.scrooge.payment.dto;

import com.scrooge.scrooge.payment.domain.PaymentHistory;
import com.scrooge.scrooge.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor //인자 없이 객체 생성 가능
public class PaymentHistoryDto {
    private Long id;
    private LocalDateTime paid_at;
    private String category;
    private Integer amount;
    private String used_at;
    private String card_name;

    /*연결*/
    private Long userId;

    @Builder
    public PaymentHistoryDto(PaymentHistory paymentHistory) {
        this.id = paymentHistory.getId();
        this.paid_at = paymentHistory.getPaid_at();
        this.category = paymentHistory.getCategory();
        this.amount = paymentHistory.getAmount();
        this.used_at = paymentHistory.getUsed_at();
        this.card_name = paymentHistory.getCard_name();
        this.userId = paymentHistory.getUser().getUserId();
    }
}
