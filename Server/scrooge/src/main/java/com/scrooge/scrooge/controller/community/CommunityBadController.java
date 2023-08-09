package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.communityDto.ArticleBadDto;
import com.scrooge.scrooge.repository.community.ArticleBadRepository;
import com.scrooge.scrooge.repository.member.MemberOwningBadgeRepository;
import com.scrooge.scrooge.service.BadgeService;
import com.scrooge.scrooge.service.community.CommunityBadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="CommunityUnlike", description = "커뮤니티 싫어요 관련 API")
@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityBadController {

    private final CommunityBadService communityBadService;
    private final MemberOwningBadgeRepository memberOwningBadgeRepository;
    private final BadgeService badgeService;
    private final ArticleBadRepository articleBadRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // 야유 기능 구현
    @PostMapping("/{articleId}/bad")
    public ResponseEntity<?> addCommunityBad(@RequestHeader("Authorization")String header, @PathVariable("articleId")Long articleId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        if (articleBadRepository.existsByArticleIdAndMemberId(articleId, memberId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 싫어요 표시한 게시글입니다.");
        }

        communityBadService.addCommunityBad(articleId, memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body("싫어요 성공");
    }

    // 아유 기능 취소 구현
    @DeleteMapping("/{articleId}/bad")
    public ResponseEntity<?> cancelCommunityBad(@RequestHeader("Authorization")String header, @PathVariable Long articleId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        if (!articleBadRepository.existsByArticleIdAndMemberId(articleId, memberId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("싫어요 먼저 하고 취소하세요.");
        }

        communityBadService.cancelCommunityBad(articleId, memberId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("싫어요 취소 성공");
    }

    // 사용자가 아유를 했는지 체크하기
    @GetMapping("/{articleId}/bad")
    public ResponseEntity<?> getCommunityBadCheck(@RequestHeader("Authorization")String header, @PathVariable("articleId")Long articleId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        ArticleBadDto articleBadDto = communityBadService.getCommunityBadCheck(articleId, jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.ok(articleBadDto);
    }
}
