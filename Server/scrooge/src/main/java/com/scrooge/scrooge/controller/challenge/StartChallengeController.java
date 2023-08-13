package com.scrooge.scrooge.controller.challenge;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.challengeDto.ChallengeStartRespDto;
import com.scrooge.scrooge.dto.challengeDto.MyChallengeMyAuthDto;
import com.scrooge.scrooge.dto.challengeDto.MyChallengeRespDto;
import com.scrooge.scrooge.service.challenge.StartChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "ChallengeStart", description = "시작된 챌린지에 관련된 API")
@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
public class StartChallengeController {

    private final StartChallengeService startChallengeService;

    private final JwtTokenProvider jwtTokenProvider;

    // 사용자가 참여한 시작된 챌린지에 대한 정보 조회
    @Operation(summary = "사용자가 참여한 시작된 챌린지에 대한 정보 조회 API")
    @GetMapping("/{challengeId}/start/my-challenge")
    public ResponseEntity<MyChallengeRespDto> getMyStartedChallenge(@PathVariable("challengeId") Long challengeId) throws Exception {
        MyChallengeRespDto myChallengeRespDto = startChallengeService.getMyStartedChallenge(challengeId);
        return ResponseEntity.ok(myChallengeRespDto);
    }

    // 사용자 인증 등록 API
    @Operation(summary = "사용자가 인증을 등록하는 API")
    @PostMapping(value = "/{challengeId}/auth", consumes = "multipart/form-data")
    public ResponseEntity<ChallengeStartRespDto> createMyChallengeAuth(@RequestHeader("Authorization") String tokenHeader, @PathVariable("challengeId") Long challengeId,
                                                                       @RequestParam MultipartFile img) throws IOException {
        ChallengeStartRespDto challengeStartRespDto = new ChallengeStartRespDto();

        String token = extractToken(tokenHeader);

        if (!jwtTokenProvider.validateToken(token)) {
            challengeStartRespDto.setStatus("Fail");
            challengeStartRespDto.setMessage("유효하지 않은 토큰입니다.");
            return ResponseEntity.ok(challengeStartRespDto);
        }

        challengeStartRespDto = startChallengeService.createMyChallengeAuth(challengeId, jwtTokenProvider.extractMemberId(token), img);
        return ResponseEntity.ok(challengeStartRespDto);
    }

    // 사용자 인증 현황 조회 API
    @Operation(summary = "사용자 인증 현황 조회 API")
    @GetMapping("/{challengeId}/my-challenge/my-auth")
    public ResponseEntity<MyChallengeMyAuthDto> getMyChallengeMyAuth(
            @RequestHeader("Authorization") String tokenHeader,
            @PathVariable("challengeId") Long challengeId) {
        String token = extractToken(tokenHeader);
        Long memberId = jwtTokenProvider.extractMemberId(token);

        MyChallengeMyAuthDto myChallengeMyAuthDto = startChallengeService.getMyChallengeMyAuth(challengeId, memberId);
        return ResponseEntity.ok(myChallengeMyAuthDto);
    }

    private String extractToken (String tokenHeader){
        if (tokenHeader != null && tokenHeader.startsWith("Bearer")) {
            return tokenHeader.substring(7);
        }
        return null;
    }
}