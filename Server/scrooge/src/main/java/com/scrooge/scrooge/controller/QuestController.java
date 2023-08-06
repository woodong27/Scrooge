package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.QuestDto;
import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.member.MemberSelectedQuestDto;
import com.scrooge.scrooge.service.QuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Quest", description = "APIs about Quest")
@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Get Quest", description = "questId를 받아서 특정 퀘스트 정보를 반환")
    @GetMapping("/{questId}")
    public ResponseEntity<QuestDto> getQuest(@PathVariable("questId") Long questId) {
        Optional<QuestDto> questDto = questService.getQuest(questId);
        return questDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get Quests", description = "전체 퀘스트 목록 중 랜덤 6개 반환")
    @GetMapping("/")
    public ResponseEntity<List<QuestDto>> getRandomQuests() {
        List<QuestDto> questDtos = questService.getRandomQuests();
        return ResponseEntity.ok(questDtos);
    }

    @Operation(summary = "퀘스트 선택 API", description = "유저별로 매주 3개의 퀘스트를 선택할 수 있음")
    @PostMapping("/{questId}/select")
    public ResponseEntity<String> selectQuest(@RequestHeader("Authorization") String tokenHeader, @PathVariable("questId") Long questId) {
        String token = jwtTokenProvider.extractToken(tokenHeader);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰 입니다.");
        }

        try {
            questService.selectQuest(questId, jwtTokenProvider.extractEmail(token));
            return ResponseEntity.ok("퀘스트 선택 완료");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @Operation(summary = "퀘스트 완료 count 증가 API", description = "퀘스트 완료 count 증가 API")
//    @PutMapping("/{questId}/complete")
//    public ResponseEntity<?> completeQuest(@RequestHeader("Authorization") String tokenHeader, @PathVariable("questId") Long questId) {
//        String token = jwtTokenProvider.extractToken(tokenHeader);
//
//        if (!jwtTokenProvider.validateToken(token)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
//        }
//
//        List<MemberSelectedQuestDto> memberSelectedQuestDtos = questService.updateCompleteCount(questId, jwtTokenProvider.extractMemberId(token));
//        return ResponseEntity.ok(memberSelectedQuestDtos);
//    }

    @Operation(summary = "유저가 선택한 퀘스트 목록 API", description = "유저가 선택한 퀘스트 목록 반환")
    @GetMapping("/member")
    public ResponseEntity<?> getMemberSelectedQuest(@RequestHeader("Authorization") String header) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        List<MemberSelectedQuestDto> memberSelectedQuestDtos = questService.getMemberSelectedQuests(jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.ok(memberSelectedQuestDtos);
    }
}

