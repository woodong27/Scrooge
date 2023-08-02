package com.scrooge.scrooge.controller.challenge;

import com.scrooge.scrooge.dto.challengeDto.ChallengeReqDto;
import com.scrooge.scrooge.dto.challengeDto.ChallengeRespDto;
import com.scrooge.scrooge.service.challenge.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name="Challenge", description = "챌린지 API")
@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    // 챌린지를 생성하는 API
    @Operation(summary = "챌린지 생성")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createChallenge(ChallengeReqDto challengeReqDto, @RequestParam List<MultipartFile> images) {
        challengeService.createChallenge(challengeReqDto, images);

        return ResponseEntity.ok(challengeReqDto);
    }

    // 전체 챌린지를 조회하는 API
    @Operation(summary = "전체 챌린지를 조회하는 API")
    @GetMapping
    public ResponseEntity<?> getAllChallenges() {
        List<ChallengeRespDto> challengeDtos = challengeService.getAllChallenges();
        return ResponseEntity.ok(challengeDtos);
    }

    // 카테고리 별 챌린지를 조회하는 API
    @Operation(summary = "카테고리 별 챌린지를 조회하는 API", description = "categoryId => 1: 식비, 2: 교통비, 3: 쇼핑, 4: 기타")
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getChallengesbyCategory(@PathVariable("categoryId") Integer categoryId) {
        List<ChallengeRespDto> challengeDtos = challengeService.getChallengesbyCategory(categoryId);
        return ResponseEntity.ok(challengeDtos);
    }

    // 마이 챌린지를 조회하는 API
    @Operation(summary = "마이 챌린지를 조회하는 API", description = "statusId => 1: 시작 전, 2: 진행 중, 3: 종료")
    @GetMapping("/{memberId}/{statusId}")
    public ResponseEntity<?> getMyChallenges(@PathVariable("memberId")Long memberId, @PathVariable("statusId")Integer statusId) {
        List<ChallengeRespDto> myChallengeDtos = challengeService.getMyChallenges(memberId, statusId);
        return ResponseEntity.ok(myChallengeDtos);
    }



}