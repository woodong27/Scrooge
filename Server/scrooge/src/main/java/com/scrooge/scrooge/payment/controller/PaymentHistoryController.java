package com.scrooge.scrooge.payment.controller;

import com.scrooge.scrooge.payment.domain.PaymentHistory;
import com.scrooge.scrooge.payment.dto.PaymentHistoryDto;
import com.scrooge.scrooge.payment.service.PaymentHistoryService;
import com.scrooge.scrooge.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    @PostMapping("/payment-history")
    public ResponseEntity<?> addPaymentHistory(@RequestBody PaymentHistoryDto paymentHistoryDto) {
        Long userId = 1L;

        //User user = userService.getUserByUserseq(userSeq); 이런 코드 필요,,
        //User user =

        PaymentHistory paymentHistory = paymentHistoryService.addPaymentHistory(userId, paymentHistoryDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
