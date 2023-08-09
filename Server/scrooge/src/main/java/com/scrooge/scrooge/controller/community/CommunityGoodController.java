package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.community.ArticleGoodRepository;
import com.scrooge.scrooge.service.community.CommunityGoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="CommunityLike", description = "커뮤니티 좋아요 관련 API")
@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityGoodController {

    private final CommunityGoodService communityGoodService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ArticleGoodRepository articleGoodRepository;

    // 환호 기능 구현
    @Operation(summary = "커뮤니티 글 좋아요 등록")
    @PostMapping("/{articleId}/good")
    public ResponseEntity<?> addCommunityGood(@RequestHeader("Authorization")String header, @PathVariable("articleId")Long articleId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        if (articleGoodRepository.existsByArticleIdAndMemberId(articleId, memberId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 좋아요 표시한 게시글입니다.");
        }

        communityGoodService.addCommunityGood(articleId, jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.status(HttpStatus.CREATED).body("좋아요 완료");
    }

    // 환호 취소 구현
    @Operation(summary = "커뮤니티 글 좋아요 취소")
    @DeleteMapping("/{articleId}/good")
    public ResponseEntity<?> cancleCommunityGood(@RequestHeader("Authorization")String header, @PathVariable("articleId")Long articleId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        if (!articleGoodRepository.existsByArticleIdAndMemberId(articleId, memberId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("좋아요 먼저 하고 취소하세요");
        }

        communityGoodService.cancleCommunityGood(articleId, jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("좋아요 취소 완료");
    }

    // 사용자가 환호를 했는지 체크하기
    @Operation(summary = "사용자가 커뮤니티 글을 좋아요 했는지 체크")
    @GetMapping("/{articleId}/good")
    public ResponseEntity<?> getCommunityGoodCheck(@RequestHeader("Authorization")String header, @PathVariable("articleId")Long articleId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰 입니다.");
        }

        ArticleGoodDto articleGoodDto = communityGoodService.getCommunityGoodCheck(articleId, jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.ok(articleGoodDto);
    }
}
