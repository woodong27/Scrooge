package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.PaymentHistory;
import com.scrooge.scrooge.dto.PaymentHistoryDto;
import com.scrooge.scrooge.repository.MemberRepository;
import com.scrooge.scrooge.repository.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PaymentHistory addPaymentHistory(Long memberId, PaymentHistoryDto paymentHistoryDto) {
        PaymentHistory paymentHistory = new PaymentHistory();

        paymentHistory.setAmount(paymentHistoryDto.getAmount());
        paymentHistory.setUsedAt(paymentHistoryDto.getUsedAt());
        paymentHistory.setCardName(paymentHistoryDto.getCardName());

        /* 연결 */
        paymentHistory.setMember(memberRepository.findById(memberId).orElse(null));

        return paymentHistoryRepository.save(paymentHistory);
    }

    // userId에 따른 전체 소비 내역 조회
    public List<PaymentHistoryDto> getPaymentHistoryByUserId(Long memberId) {
        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByMemberId(memberId);
        return paymentHistories.stream()
                .map(PaymentHistoryDto::new)
                .collect(Collectors.toList());
    }

    // userId의 오늘 전체 소비 내역 조회
    public List<PaymentHistoryDto> getPaymentHistoryByUserIdToday(Long memberId) {
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByMemberIdAndPaidAtBetween(memberId, todayStart, todayEnd);
        return paymentHistories.stream()
                .map(PaymentHistoryDto::new)
                .collect(Collectors.toList());
    }

    // userId 각각의 paymentHistory 조회
    public PaymentHistoryDto getPaymentHistoryEach(Long memberId, Long paymentHistoryId) {
        PaymentHistory paymentHistory = paymentHistoryRepository.findByIdAndMemberId(paymentHistoryId, memberId);
        if(paymentHistory == null) return null;
        return new PaymentHistoryDto(paymentHistory);
    }

    // 소비내역을 수정하는 비즈니스 로직
    public PaymentHistory updatePaymentHistory(Long memberId, PaymentHistoryDto paymentHistoryDto) throws AccessDeniedException {
        PaymentHistory paymentHistory = paymentHistoryRepository.findById(paymentHistoryDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("PaymentHistory not found with id: " + paymentHistoryDto.getId()));

        // 입력받은 memberId와 findById로 찾은 paymentHistory의 memberId가 같은지 확인
        if(!paymentHistory.getMember().getId().equals(memberId)) {
            throw new AccessDeniedException("memberId가 PaymentHistory의 userId와 다릅니다.");
        }

        // 변경사항 반영
        paymentHistory.setAmount(paymentHistoryDto.getAmount());
        paymentHistory.setCardName(paymentHistoryDto.getCardName());
        paymentHistory.setCategory(paymentHistoryDto.getCategory());
        paymentHistory.setUsedAt(paymentHistoryDto.getUsedAt());
        paymentHistory.setPaidAt(paymentHistoryDto.getPaidAt());

        return paymentHistoryRepository.save(paymentHistory);
    }



}
