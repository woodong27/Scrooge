package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.PaymentHistory;
import com.scrooge.scrooge.dto.PaymentHistoryDto;
import com.scrooge.scrooge.repository.PaymentHistoryRepository;
import com.scrooge.scrooge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public PaymentHistory addPaymentHistory(Long userId, PaymentHistoryDto paymentHistoryDto) {
        PaymentHistory paymentHistory = new PaymentHistory();

        paymentHistory.setAmount(paymentHistoryDto.getAmount());
        paymentHistory.setUsed_at(paymentHistoryDto.getUsed_at());
        paymentHistory.setCard_name(paymentHistoryDto.getCard_name());

        /* 연결 */
        paymentHistory.setUser(userRepository.findByUserId(userId));

        return paymentHistoryRepository.save(paymentHistory);

    }



}
