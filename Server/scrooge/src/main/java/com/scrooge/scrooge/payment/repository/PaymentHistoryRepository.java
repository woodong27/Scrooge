package com.scrooge.scrooge.payment.repository;

import com.scrooge.scrooge.payment.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
}
