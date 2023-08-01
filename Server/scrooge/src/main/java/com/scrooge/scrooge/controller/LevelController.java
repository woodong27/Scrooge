package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.member.MemberDto;
import com.scrooge.scrooge.service.LevelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Level", description = "Level에 관련된 API 입니다.")
@RestController
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    /* 경험치 획득 관련 */
    // 일일 정산 후 경험치 획득
    @Operation(summary = "일일 정산 후 경험치 획득")
    @PutMapping("/exp/daily-settlement")
    public ResponseEntity<?> updateExpAfterDailySettlement(@RequestBody MemberDto memberDto) {
        MemberDto memberDto1 = levelService.updateExpAfterDailySettlement(memberDto);
        return ResponseEntity.ok(memberDto1);
    }


}
