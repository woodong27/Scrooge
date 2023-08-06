package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.domain.PaymentHistory;
import com.scrooge.scrooge.dto.paymentHistory.PaymentHistoryDto;
import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.dto.member.MemberDto;
import com.scrooge.scrooge.dto.paymentHistory.PaymentHistoryRespDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.service.PaymentHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Optional;


import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="PaymentHistory", description = "소비내역 API")
@RestController
@RequestMapping("/payment-history")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentHistoryController {

    private final JwtTokenProvider jwtTokenProvider;

    private final PaymentHistoryService paymentHistoryService;
    private final MemberRepository memberRepository;
    public boolean isSettlementDone = false;

    @Operation(summary = "소비내역을 등록하는 API", description = "소비내역 등록")
    @PostMapping
    public ResponseEntity<PaymentHistoryRespDto> addPaymentHistory(@RequestBody PaymentHistoryDto paymentHistoryDto, @RequestHeader("Authorization")String tokenHeader) {
        System.out.println("실행되었나요?");

        String token = extractToken(tokenHeader);

        Long memberId = jwtTokenProvider.extractMemberId(token);

        Optional<Member> member = memberRepository.findById(memberId);

        PaymentHistoryRespDto paymentHistoryRespDto = paymentHistoryService.addPaymentHistory(memberId, paymentHistoryDto);
        return new ResponseEntity<>(paymentHistoryRespDto, HttpStatus.OK);
    }

    // Member별 소비내역 전체를 조회하는 API (이거!)
    @Operation(summary = "Member별 소비내역 전체를 조회하는 API", description = "소비내역 조회")
    @GetMapping
    public ResponseEntity<List<PaymentHistoryDto>> selectPaymentHistory(@RequestHeader("Authorization")String tokenHeader) {
        String token = extractToken(tokenHeader);

        Long memberId = jwtTokenProvider.extractMemberId(token);
        List<PaymentHistoryDto> paymentHistoryDtos = paymentHistoryService.getPaymentHistoryByMemberId(memberId);
        return ResponseEntity.ok(paymentHistoryDtos);
    }

    // 오늘 member의 소비내역 전체를 조회하는 API (이거!)
    @Operation(summary = "오늘 member의 소비내역 전체를 조회하는 API", description = "오늘 소비내역 조회")
    @GetMapping("/today")
    public ResponseEntity<List<PaymentHistoryDto>> selectPaymentHistoryByMemberIdToday(@RequestHeader("Authorization")String tokenHeader) {
        String token = extractToken(tokenHeader);
        Long memberId = jwtTokenProvider.extractMemberId(token);

        List<PaymentHistoryDto> paymentHistoryDtos = paymentHistoryService.getPaymentHistoryByMemberIdToday(memberId);
        return ResponseEntity.ok(paymentHistoryDtos);
    }

    // Member의 소비내역 하나를 조회하는 API (이거!)
    @Operation(summary = "member의 소비내역 하나를 조회하는 API", description = "member의 소비내역 하나를 조회하는 API")
    @GetMapping("/{paymentHistoryId}")
    public ResponseEntity<PaymentHistoryDto> selectPaymentHistoryEach(
            @RequestHeader("Authorization")String tokenHeader,
            @PathVariable("paymentHistoryId")Long paymentHistoryId
    ) {
        String token = extractToken(tokenHeader);
        Long memberId = jwtTokenProvider.extractMemberId(token);

        PaymentHistoryDto paymentHistoryDto = paymentHistoryService.getPaymentHistoryEach(memberId, paymentHistoryId);
        if(paymentHistoryDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentHistoryDto);
    }

    // memberId를 가진 사용자의 paymentId를 가진 소비내역을 수정한다. (이거!)
    @Operation(summary = "memberId를 가진 사용자의 paymentId를 가진 소비내역을 수정하는 API", description = "소비내역 수정")
    @PutMapping("/{paymentHistoryId}")
    public ResponseEntity<?> updatePaymentHistory(
            @RequestHeader("Authorization")String tokenHeader,
            @PathVariable("paymentHistoryId")Long paymentHistoryId,
            @RequestBody PaymentHistoryDto paymentHistoryDto
    ) {
        String token = extractToken(tokenHeader);
        Long memberId = jwtTokenProvider.extractMemberId(token);

        paymentHistoryDto.setId(paymentHistoryId);
        SuccessResp successResp = new SuccessResp(1);
        try{
            PaymentHistory updatePaymentHistory = paymentHistoryService.updatePaymentHistory(memberId, paymentHistoryDto);
            return new ResponseEntity<>(successResp, HttpStatus.OK);
        } catch(AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("memberId와 paymentHistory의 memberId가 일치하지 않습니다.");
        }
    }

    // 하루에 한 번 정산하면 경험치 +100 해주는 API
    @Operation(summary = "일일 정산 후 경험치 획득")
    @PutMapping("/settlement-exp")
    public ResponseEntity<?> updateExpAfterDailySettlement(@RequestHeader("Authorization") String tokenHeader) {

        if (isSettlementDone) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("오늘은 이미 정산 했습니다.");
        }

        isSettlementDone = true;

        String token = extractToken(tokenHeader);

        if(!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        MemberDto memberDto = paymentHistoryService.updateExpAfterDailySettlement(jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.ok(memberDto);
    }

    // 하루 전체 소비 금액 조회하는 API
    @Operation(summary = "하루 전체 소비 금액 조회하는 API")
    @GetMapping("/today-total")
    public ResponseEntity<Integer> getTodayTotalConsumption(@RequestHeader("Authorization") String tokenHeader) {
        String token = extractToken(tokenHeader);

        Integer todayTotalConsumption = paymentHistoryService.getTodayTotalConsumption(jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.ok(todayTotalConsumption);
    }


    private String extractToken(String tokenHeader) {
        if(tokenHeader != null && tokenHeader.startsWith("Bearer")) {
            return tokenHeader.substring(7);
        }
        return null;
    }


}
