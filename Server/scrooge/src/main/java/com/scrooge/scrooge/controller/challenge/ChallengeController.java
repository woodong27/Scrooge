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



}
