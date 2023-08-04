package com.scrooge.scrooge.dto.paymentHistory;

import com.scrooge.scrooge.domain.PaymentHistory;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentHistoryRespDto {

    private Long id;
    private Integer success;

}
