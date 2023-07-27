package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.PaymentHistory;
import com.scrooge.scrooge.dto.PaymentHistoryDto;
import com.scrooge.scrooge.repository.PaymentHistoryRepository;
import com.scrooge.scrooge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public PaymentHistory addPaymentHistory(Long userId, PaymentHistoryDto paymentHistoryDto) {
        PaymentHistory paymentHistory = new PaymentHistory();

        paymentHistory.setAmount(paymentHistoryDto.getAmount());
        paymentHistory.setUsedAt(paymentHistoryDto.getUsedAt());
        paymentHistory.setCardName(paymentHistoryDto.getCardName());

        /* 연결 */
        paymentHistory.setUser(userRepository.findById(userId).orElse(null));

        return paymentHistoryRepository.save(paymentHistory);
    }

    // userId에 따른 소비 내역 출력
    public List<PaymentHistoryDto> getPaymentHistoryByUserId(Long userId) {
        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByUserId(userId);
        return paymentHistories.stream()
                .map(PaymentHistoryDto::new)
                .collect(Collectors.toList());
    }




}
