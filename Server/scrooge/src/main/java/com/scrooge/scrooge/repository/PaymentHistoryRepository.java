package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    @Query("SELECT pmh FROM PaymentHistory pmh WHERE pmh.user.id = ?1")
    List<PaymentHistory> findByUserId(Long userId);
    List<PaymentHistory> findByUserIdAndPaidAtBetween(Long userId, LocalDateTime todayStart, LocalDateTime todayEnd);
}
