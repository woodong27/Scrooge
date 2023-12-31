package com.scrooge.scrooge.controller.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.challengeDto.*;
import com.scrooge.scrooge.repository.challenge.ChallengeParticipantRepository;
import com.scrooge.scrooge.repository.challenge.ChallengeRepository;
import com.scrooge.scrooge.service.challenge.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name="Challenge", description = "챌린지 API")
@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChallengeParticipantRepository challengeParticipantRepository;
    private final ChallengeRepository challengeRepository;

    // 챌린지를 생성하는 API
    @Operation(summary = "챌린지 생성")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createChallenge(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestPart("info") String info,
            @RequestParam("images") List<MultipartFile> images) throws IOException {

        String token = extractToken(tokenHeader);
        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        ObjectMapper objectMapper = new ObjectMapper();
        ChallengeReqDto challengeReqDto = objectMapper.readValue(info, ChallengeReqDto.class);
        challengeReqDto.setChallengeMasterId(memberId);

        return ResponseEntity.ok(challengeService.createChallenge(challengeReqDto, images));
    }

    // 전체 챌린지를 조회하는 API
    @Operation(summary = "전체 챌린지를 조회하는 API")
    @GetMapping
    public ResponseEntity<List<ChallengeResDto>> getAllChallenges() {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    // 카테고리 별 챌린지를 조회하는 API
    @Operation(summary = "카테고리 별 챌린지를 조회하는 API", description = "categoryId => 1: 식비, 2: 교통비, 3: 쇼핑, 4: 기타")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ChallengeResDto>> getChallengesbyCategory(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(challengeService.getChallengesbyCategory(categoryId));
    }

    // 마이 챌린지를 조회하는 API
    @Operation(summary = "마이 챌린지 중에서 시작 전 챌린지를 조회하는 API", description = "statusId => 1: 시작 전, 2: 진행 중, 3: 종료")
    @GetMapping("/{statusId}/member")
    public ResponseEntity<?> getMyChallenges(
            @RequestHeader("Authorization")String tokenHeader,
            @PathVariable("statusId")Integer statusId) {

        String token = extractToken(tokenHeader);
        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        return ResponseEntity.ok(challengeService.getMyChallenges(jwtTokenProvider.extractMemberId(token), statusId));
    }

    // 챌린지 상세 조회 API
    // 여기만 예시이미지 목록 필요
    @Operation(summary = "챌린지를 상세 조회하는 API")
    @GetMapping("/{challengeId}")
    public ResponseEntity<ChallengeDetailDto> getChallenge(@PathVariable("challengeId") Long challengeId) {
        return ResponseEntity.ok(challengeService.getChallenge(challengeId));
    }

    // 사용자가 챌린지를 참여하는 API
    @Operation(summary = "사용자가 챌린지에 참여하는 API")
    @PostMapping("/{challengeId}/participate")
    public ResponseEntity<?> participateInChallenge(@RequestHeader("Authorization") String tokenHeader, @PathVariable("challengeId") Long challengeId) {
        String token = extractToken(tokenHeader);
        if(!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        if (challengeParticipantRepository.existsByChallengeIdAndMemberId(challengeId, memberId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 참여한 챌린지 입니다.");
        }

        return ResponseEntity.ok(challengeService.participateInChallenge(challengeId, memberId));
    }

    // 챌린지 마스터가 챌린지를 시작하는 API
    @Operation(summary = "챌린지 마스터가 챌린지를 시작하는 API")
    @PutMapping("/{challengeId}/start")
    public ResponseEntity<?> startChallenge(@RequestHeader("Authorization")String header, @PathVariable("challengeId") Long challengeId) {
        String token = jwtTokenProvider.extractToken(header);
        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        if (!challengeRepository.existsByIdAndChallengeMasterId(challengeId, jwtTokenProvider.extractMemberId(token))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("챌린지 마스터만 챌린지를 시작할 수 있습니다.");
        }

        ChallengeStartRespDto challengeStartRespDto = challengeService.startChallenge(challengeId);
        return ResponseEntity.ok(challengeStartRespDto);
    }

    private String extractToken(String tokenHeader) {
        if(tokenHeader != null && tokenHeader.startsWith("Bearer")) {
            return tokenHeader.substring(7);
        }
        return null;
    }




}
