package com.scrooge.scrooge.controller.challenge;

import com.scrooge.scrooge.dto.challengeDto.ChallengeStartRespDto;
import com.scrooge.scrooge.dto.challengeDto.MyChallengeRespDto;
import com.scrooge.scrooge.service.challenge.ChallengeService;
import com.scrooge.scrooge.service.challenge.StartChallengeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ChallengeStart", description = "시작된 챌린지에 관련된 API")
@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class StartChallengeController {

    private final StartChallengeService startChallengeService;

    // 사용자가 참여한 시작된 챌린지에 대한 정보 조회
    @GetMapping("/{challengeId}/my-challenge")
    public ResponseEntity<MyChallengeRespDto> getMyStartedChallenge(@PathVariable("challengeId") Long challengeId) throws IllegalAccessException {
        MyChallengeRespDto myChallengeRespDto = startChallengeService.getMyStartedChallenge(challengeId);
        return ResponseEntity.ok(myChallengeRespDto);
    }

}
