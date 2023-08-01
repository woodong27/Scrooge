package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.QuestDto;
import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
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

    @Operation(summary = "Get Quests", description = "전체 퀘스트 목록을 반환")
    @GetMapping("/")
    public ResponseEntity<List<QuestDto>> getAllQuests() {
        List<QuestDto> questDtos = questService.getAllQuests();
        return ResponseEntity.ok(questDtos);
    }

    @PostMapping("/{questId}/select")
    public ResponseEntity<String> selectQuest(@RequestHeader("Authorization") String tokenHeader, @PathVariable("questId") Long questId) {
        String token = extractToken(tokenHeader);

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


    private String extractToken(String header) {
        if (header != null && header.startsWith("Bearer")) {
            return header.substring(7);
        }
        return null;
    }
}

