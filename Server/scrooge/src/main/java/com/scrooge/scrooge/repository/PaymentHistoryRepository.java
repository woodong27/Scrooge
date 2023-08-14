package com.scrooge.scrooge.repository;

import com.scrooge.scrooge.domain.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
    @Query("SELECT pmh FROM PaymentHistory pmh WHERE pmh.member.id = ?1")
    List<PaymentHistory> findByMemberId(Long memberId);
    List<PaymentHistory> findByMemberIdAndPaidAtBetween(Long memberId, LocalDateTime todayStart, LocalDateTime todayEnd);
    PaymentHistory findByIdAndMemberId(Long paymentHistoryId, Long memberId);

    @Query("SELECT COUNT(pmh) FROM PaymentHistory pmh WHERE pmh.member.id = :memberId")
    Integer countByMemberId(Long memberId);
}
