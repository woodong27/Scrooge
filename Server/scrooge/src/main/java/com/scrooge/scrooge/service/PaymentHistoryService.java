package com.scrooge.scrooge.service;

import com.scrooge.scrooge.domain.PaymentHistory;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.DateTimeReqDto;
import com.scrooge.scrooge.dto.paymentHistory.PaymentHistoryDto;
import com.scrooge.scrooge.dto.member.MemberDto;
import com.scrooge.scrooge.dto.paymentHistory.RecapDto;
import com.scrooge.scrooge.repository.LevelRepository;
import com.scrooge.scrooge.repository.member.MemberOwningBadgeRepository;
import com.scrooge.scrooge.dto.paymentHistory.PaymentHistoryRespDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.PaymentHistoryRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final MemberRepository memberRepository;
    private final QuestService questService;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;
    private final BadgeService badgeService;
    private final MemberOwningBadgeRepository memberOwningBadgeRepository;

    private final LevelService levelService;

    @Transactional
    public PaymentHistoryRespDto addPaymentHistory(Long memberId, PaymentHistoryDto paymentHistoryDto) {
        PaymentHistory paymentHistory = new PaymentHistory();
        PaymentHistoryRespDto paymentHistoryRespDto = new PaymentHistoryRespDto();

        paymentHistory.setAmount(paymentHistoryDto.getAmount());
        paymentHistory.setUsedAt(paymentHistoryDto.getUsedAt());
        paymentHistory.setCardName(paymentHistoryDto.getCardName());
        paymentHistory.setIsSettled(false);

        if(paymentHistoryDto.getPaidAt() == null) {
            paymentHistory.setPaidAt(LocalDateTime.now());
        }
        else {
            paymentHistory.setPaidAt(paymentHistoryDto.getPaidAt());
        }

        /* 연결 */

        // memberId에 맞는 member 가져오기
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            paymentHistory.setMember(member.get());

            member.get().setWeeklyConsum(member.get().getWeeklyConsum() + paymentHistoryDto.getAmount()); // 주간 소비량에 소비내역 가격 더해주기
            memberRepository.save(member.get());

            paymentHistoryRepository.save(paymentHistory);

            paymentHistoryRespDto.setSuccess(1);
            paymentHistoryRespDto.setId(paymentHistory.getId());

            return paymentHistoryRespDto;
        }
        else {
            throw new NotFoundException(memberId + "에 해당하는 member를 찾을 수 없습니다.");
        }
    }

    // userId에 따른 전체 소비 내역 조회
    public List<PaymentHistoryDto> getPaymentHistoryByMemberId(Long memberId, String dateTime) {

        // 날짜 문자열을 LocalDate로 변환하기
        LocalDate date = LocalDate.parse(dateTime);

        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByMemberId(memberId);

        List<PaymentHistory> filteredPaymentHistories = paymentHistories.stream()
                .filter(paymentHistory -> paymentHistory.getPaidAt().toLocalDate().equals(date))
                .collect(Collectors.toList());

        return filteredPaymentHistories.stream()
                .map(PaymentHistoryDto::new)
                .collect(Collectors.toList());
    }

    // 사용자 별 월 별 소비 내역 조회
    public List<PaymentHistoryDto> getPaymentHistoryPerMonth(Long memberId, String dateStr) {

        // 입력받은 날짜 문자열을 LocalDate로 변환
        LocalDate date = LocalDate.parse(dateStr + "-01");

        // 해당 월의 시작일과 종료일 계산하기
        LocalDate startDate = YearMonth.of(date.getYear(), date.getMonth()).atDay(1);
        LocalDate endDate = YearMonth.of(date.getYear(), date.getMonth()).atEndOfMonth();

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByMemberIdAndPaidAtBetween(memberId, startDateTime, endDateTime);
        return paymentHistories.stream()
                .map(PaymentHistoryDto::new)
                .collect(Collectors.toList());
    }

    // userId의 오늘 전체 소비 내역 조회
    public List<PaymentHistoryDto> getPaymentHistoryByMemberIdToday(Long memberId) {
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

        // 이전 : paymentHistory 수정 후 : paymentHistoryDto
        if(!paymentHistory.getAmount().equals(paymentHistoryDto.getAmount())) {
            // memberId에 맞는 member 가져오기
            Optional<Member> member = memberRepository.findById(memberId);
            if(member.isPresent()) {
                member.get().setWeeklyConsum(member.get().getWeeklyConsum() - paymentHistory.getAmount() + paymentHistoryDto.getAmount());
                memberRepository.save(member.get());
            }
            else {
                throw new NotFoundException(memberId + "를 가진 사용자는 존재하지 않습니다.");
            }
        }

        // 변경사항 반영
        paymentHistory.setAmount(paymentHistoryDto.getAmount());
        paymentHistory.setCardName(paymentHistoryDto.getCardName());
        paymentHistory.setCategory(paymentHistoryDto.getCategory());
        paymentHistory.setUsedAt(paymentHistoryDto.getUsedAt());
        paymentHistory.setIsSettled(true);

        return paymentHistoryRepository.save(paymentHistory);
    }

    public MemberDto updateExpAfterDailySettlement(Long memberId) {
        Member member =  memberRepository.findWithRelatedEntitiesById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 멤버를 찾을 수 없습니다."));

        // 오늘의 첫 정산일 경우에
        if (!member.getIsSettlementDone()) {
            // 경험치 +100 정산해주기
            member.setExp(member.getExp() + 100);
            levelService.levelUp(member);

            // 스트릭 1 증가
            int currentStreak = member.getStreak();
            member.setStreak(currentStreak + 1);
            // 스트릭 max값 갱신
            if(member.getMaxStreak() < member.getStreak()) {
                member.setMaxStreak(member.getStreak());
            }

            // SettlementDone true로 변경
            member.setIsSettlementDone(true);

            memberRepository.save(member);

            // 정산하기 관련 퀘스트 소지 시 퀘스트 완료 진행
            if (memberSelectedQuestRepository.existsByMemberIdAndQuestId(memberId, 1L)) {
                questService.completeQuest(1L, memberId);
            }

            // 정산하기 관련 뱃지 획득
            // 첫번째 정산하기 완료시 뱃지 부여
            if (currentStreak == 0 || !memberOwningBadgeRepository.existsByBadgeIdAndMemberId(1L, memberId)) {
                badgeService.giveBadge(1L, memberId);
            }
            // 7번째 정산하기 완료시 뱃시 증정
            if (currentStreak == 6 || !memberOwningBadgeRepository.existsByBadgeIdAndMemberId(2L, memberId)) {
                badgeService.giveBadge(2L, memberId);
            }
            // 30번째 정산하기 완료시 뱃지 증정
            if (currentStreak == 29 || !memberOwningBadgeRepository.existsByBadgeIdAndMemberId(3L, memberId)) {
                badgeService.giveBadge(3L, memberId);
            }
        }
        return new MemberDto(member);
    }

    // 하루 전체 소비 금액 조회하는 API
    public Integer getTodayTotalConsumption(Long memberId) {
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByMemberIdAndPaidAtBetween(memberId, todayStart, todayEnd);

        Integer todayTotalConsumption = 0;

        for(PaymentHistory paymentHistory : paymentHistories) {
            todayTotalConsumption += paymentHistory.getAmount();
        }

        return todayTotalConsumption;
    }

    // 날짜를 입력 받아서 소비 금액 조회하는 API
    public Integer getDateTotalConsumption(Long memberId, String dateTime) {

        LocalDate date = LocalDate.parse(dateTime);

        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByMemberId(memberId);

        List<PaymentHistory> filterPaymentHistories = paymentHistories.stream()
                .filter(paymentHistory -> paymentHistory.getPaidAt().toLocalDate().equals(date))
                .collect(Collectors.toList());

        Integer total = 0;

        for(PaymentHistory paymentHistory : filterPaymentHistories) {
            total += paymentHistory.getAmount();
        }

        return total;
    }


    // 내 소비에 대한 리캡을 조회하는 API
    public RecapDto getMyRecap(Long memberId) {
        // 소비내역 이번달 기준으로 ,,,
        RecapDto recapDto = new RecapDto();

        LocalDate date = LocalDate.now();

        // 해당 월의 시작일과 종료일 계산하기
        LocalDate startDate = YearMonth.of(date.getYear(), date.getMonth()).atDay(1);
        LocalDate endDate = YearMonth.of(date.getYear(), date.getMonth()).atEndOfMonth();

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        List<PaymentHistory> paymentHistoryList = paymentHistoryRepository.findByMemberIdAndPaidAtBetween(memberId, startDateTime, endDateTime);
        if(paymentHistoryList.size() == 0) {
            // 소비내역이 없음 ,, 정산 내역 없다고 false로 반환!
            recapDto.setHasPaymentHistory(false);
        }
        else {
            recapDto.setHasPaymentHistory(true);
            // 소비내역이 있다면 리캡 시작
            // 1. 많이 쓴 카테고리
            Map<String, Integer> categoryFrequency = new HashMap<>();
            // 2. 시간대 처리
            Map<String, Integer> timeOfDayFrequency = new HashMap<>();

            for(PaymentHistory paymentHistory : paymentHistoryList) {
                String category = paymentHistory.getCategory();
                categoryFrequency.put(category, categoryFrequency.getOrDefault(category, 0) + 1);

                LocalDateTime paidAt = paymentHistory.getPaidAt();
                int hour = paidAt.getHour();
                if(hour < 6) {
                    timeOfDayFrequency.put("새벽", timeOfDayFrequency.getOrDefault("새벽", 0) + 1);
                }
                else if(hour < 12) {
                    timeOfDayFrequency.put("오전", timeOfDayFrequency.getOrDefault("오전", 0) + 1);
                }
                else if(hour < 18) {
                    timeOfDayFrequency.put("오후", timeOfDayFrequency.getOrDefault("오후", 0) + 1);
                }
                else {
                    timeOfDayFrequency.put("밤", timeOfDayFrequency.getOrDefault("밤", 0) + 1);
                }
            }

            // 가장 돈을 많이 쓴 카테고리
            String mostUsedCategory = null;
            int maxFrequency = 0;

            for(Map.Entry<String, Integer> entry : categoryFrequency.entrySet()) {
                if(entry.getValue() > maxFrequency) {
                    maxFrequency= entry.getValue();
                    mostUsedCategory = entry.getKey();
                }
            }
            recapDto.setCategory(mostUsedCategory);

            // 시간대별로 가장 많이 지출한 시간대
            String mostSpendTimeOfDay = null;
            int maxTimeOfDayFrequency = 0;

            for(Map.Entry<String, Integer> entry : timeOfDayFrequency.entrySet()) {
                if(entry.getValue() > maxTimeOfDayFrequency) {
                    maxTimeOfDayFrequency = entry.getValue();
                    mostSpendTimeOfDay = entry.getKey();
                }
            }
            recapDto.setMostSpendTime(mostSpendTimeOfDay);


            // 3. 최대 스트릭
            // member 가져오기
            Optional<Member> member = memberRepository.findWithRelatedEntitiesById(memberId);
            if(member.isPresent()) {
                int maxStreak = member.get().getMaxStreak();
                recapDto.setMaxStreak(maxStreak); // 최대 스트릭

                // 최대 스트릭 상위 몇 프로 인지 ,,, 반환
                List<Member> sortedMembers = memberRepository.findByOrderByMaxStreakDesc();
                int totalMembers = sortedMembers.size();
                int myMaxStreak = recapDto.getMaxStreak();

                int rank = -1; // 초기 순위 설정

                for(int i=0; i<totalMembers; i++) {
                    if(sortedMembers.get(i).getMaxStreak() == myMaxStreak) {
                        rank = i + 1;
                        break;
                    }
                }

                if(rank != -1) {
                    double percentage = ((double) rank / totalMembers) * 100;
                    double roundedPercentage = Math.round(percentage * 10.0) / 10.0;

                    recapDto.setTopStreakPercentage(roundedPercentage); // 상위 몇 퍼센트인지 적용
                }
            }
            else {
                throw new NotFoundException(memberId + "에 해당하는 member를 찾지 못했습니다.");
            }

        }

        return recapDto;
    }
}
