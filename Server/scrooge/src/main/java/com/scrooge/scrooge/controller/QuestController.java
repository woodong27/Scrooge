package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.domain.Quest;
import com.scrooge.scrooge.dto.QuestDto;
import com.scrooge.scrooge.service.QuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Quest", description = "APIs about Quest")
@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

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
}
