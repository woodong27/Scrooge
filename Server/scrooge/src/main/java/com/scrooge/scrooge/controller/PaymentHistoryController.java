package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.domain.PaymentHistory;
import com.scrooge.scrooge.domain.User;
import com.scrooge.scrooge.dto.PaymentHistoryDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.service.PaymentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;
    private final UserRepository userRepository;

    @PostMapping("/payment-history/{userId}")
    public ResponseEntity<?> addPaymentHistory(@RequestBody PaymentHistoryDto paymentHistoryDto, @PathVariable("userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);

        //User user = userService.getUserByUserseq(userSeq); 이런 코드 필요,,
        //User user =

        PaymentHistory paymentHistory = paymentHistoryService.addPaymentHistory(userId, paymentHistoryDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
