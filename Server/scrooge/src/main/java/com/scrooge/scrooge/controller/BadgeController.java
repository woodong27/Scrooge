package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.BadgeDto;
import com.scrooge.scrooge.service.BadgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Badge", description = "APIs about Badge")
@RestController
@RequestMapping("/badge")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Get Badges", description = "Get all badges")
    @GetMapping()
    public ResponseEntity<List<BadgeDto>> getBadges() {
        List<BadgeDto> badgeDtos = badgeService.getBadges();
        return ResponseEntity.ok(badgeDtos);
    }

    @Operation(summary = "Select MainBadge", description = "Select main badge of member")
    @PutMapping("/{badgeId}")
    public ResponseEntity<?> selectMainBadge(@RequestHeader("Authorization")String header, @PathVariable("badgeId") Long badgeId) {
        String token = extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰 입니다.");
        }

        return ResponseEntity.ok(badgeService.selectMainBadge(badgeId, jwtTokenProvider.extractMemberId(token)));
    }

    private String extractToken(String header) {
        if (header != null && header.startsWith("Bearer")) {
            return header.substring(7);
        }
        return null;
    }
}
