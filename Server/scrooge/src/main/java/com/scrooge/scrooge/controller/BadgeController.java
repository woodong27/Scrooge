package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.BadgeDto;
import com.scrooge.scrooge.repository.member.MemberOwningBadgeRepository;
import com.scrooge.scrooge.service.BadgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Badge", description = "APIs about Badge")
@RestController
@RequestMapping("/badge")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberOwningBadgeRepository memberOwningBadgeRepository;

    @Operation(summary = "Get Badges", description = "Get all badges")
    @GetMapping()
    public ResponseEntity<List<BadgeDto>> getBadges() {
        List<BadgeDto> badgeDtos = badgeService.getBadges();
        return ResponseEntity.ok(badgeDtos);
    }

    @Operation(summary = "Get Badge", description = "Get badge")
    @GetMapping("/{badgeId}")
    public ResponseEntity<BadgeDto> getOneBadge(@PathVariable("badgeId") Long badgeId) {
        BadgeDto badgeDto = badgeService.getOneBadge(badgeId);
        return ResponseEntity.ok(badgeDto);
    }

    @Operation(summary = "Select MainBadge", description = "Select main badge of member")
    @PutMapping("/{badgeId}")
    public ResponseEntity<?> selectMainBadge(@RequestHeader("Authorization")String header, @PathVariable("badgeId") Long badgeId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰 입니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        if (memberOwningBadgeRepository.existsByBadgeIdAndMemberId(badgeId, memberId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당 뱃지를 소유하고 있지 않습니다.");
        }

        return ResponseEntity.ok(badgeService.selectMainBadge(badgeId, memberId));
    }

}
