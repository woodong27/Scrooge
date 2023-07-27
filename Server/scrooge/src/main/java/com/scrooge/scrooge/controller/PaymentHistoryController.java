package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.domain.PaymentHistory;
import com.scrooge.scrooge.domain.User;
import com.scrooge.scrooge.dto.PaymentHistoryDto;
import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.repository.UserRepository;
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

import java.util.Optional;

@Tag(name="PaymentHistory", description = "소비내역 API")
@RestController
@RequestMapping("/payment-history")
@RequiredArgsConstructor
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;
    private final UserRepository userRepository;

    @Operation(summary = "소비내역을 등록하는 API", description = "소비내역 등록")
    @PostMapping("/{userId}")
    public ResponseEntity<?> addPaymentHistory(@RequestBody PaymentHistoryDto paymentHistoryDto, @PathVariable("userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);

        SuccessResp successResp = new SuccessResp(1);
        PaymentHistory paymentHistory = paymentHistoryService.addPaymentHistory(userId, paymentHistoryDto);
        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // user별 소비내역 전체를 조회하는 API
    @Operation(summary = "user별 소비내역 전체를 조회하는 API", description = "소비내역 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<List<PaymentHistoryDto>> selectPaymentHistory(@PathVariable("userId") Long userId) {
        List<PaymentHistoryDto> paymentHistoryDtos = paymentHistoryService.getPaymentHistoryByUserId(userId);
        return ResponseEntity.ok(paymentHistoryDtos);
    }

    // 오늘 user의 소비내역 전체를 조회하는 API
    @Operation(summary = "오늘 user의 소비내역 전체를 조회하는 API", description = "오늘 소비내역 조회")
    @GetMapping("/{userId}/today")
    public ResponseEntity<List<PaymentHistoryDto>> selectPaymentHistoryByUserIdToday(@PathVariable("userId") Long userId) {
        List<PaymentHistoryDto> paymentHistoryDtos = paymentHistoryService.getPaymentHistoryByUserIdToday(userId);
        return ResponseEntity.ok(paymentHistoryDtos);
    }

    // user의 소비내역 하나를 조회하는 API
    @Operation(summary = "user의 소비내역 하나를 조회하는 API", description = "user의 소비내역 하나를 조회하는 API")
    @GetMapping("/{userId}/{paymentHistoryId}")
    public ResponseEntity<PaymentHistoryDto> selectPaymentHistoryEach(
            @PathVariable("userId")Long userId,
            @PathVariable("paymentHistoryId")Long paymentHistoryId
    ) {
        PaymentHistoryDto paymentHistoryDto = paymentHistoryService.getPaymentHistoryEach(userId, paymentHistoryId);
        if(paymentHistoryDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentHistoryDto);
    }

    // userId를 가진 사용자의 paymentId를 가진 소비내역을 수정한다.
    @Operation(summary = "userId를 가진 사용자의 paymentId를 가진 소비내역을 수정하는 API", description = "소비내역 수정")
    @PutMapping("/{userId}/{paymentHistoryId}")
    public ResponseEntity<?> updatePaymentHistory(
            @PathVariable("userId")Long userId,
            @PathVariable("paymentHistoryId")Long paymentHistoryId,
            @RequestBody PaymentHistoryDto paymentHistoryDto
    ) {
        paymentHistoryDto.setId(paymentHistoryId);
        SuccessResp successResp = new SuccessResp(1);
        try{
            PaymentHistory updatePaymentHistory = paymentHistoryService.updatePaymentHistory(userId, paymentHistoryDto);
            return new ResponseEntity<>(successResp, HttpStatus.OK);
        } catch(AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("userId와 paymentHistory의 userId가 일치하지 않습니다.");
        }
    }



}
