package com.scrooge.scrooge.user.repository;

import com.scrooge.scrooge.user.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
}
