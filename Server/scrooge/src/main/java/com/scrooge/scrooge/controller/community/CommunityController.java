package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import com.scrooge.scrooge.dto.communityDto.ArticleContentDto;
import com.scrooge.scrooge.dto.communityDto.ArticleDto;
import com.scrooge.scrooge.dto.communityDto.ArticleReviewCountDto;
import com.scrooge.scrooge.service.community.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.List;

@Tag(name="Community", description = "커뮤니티 API")
@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {


    private final CommunityService communityService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "커뮤니티 글 등록")
    @PostMapping(consumes="multipart/form-data")
    public ResponseEntity<?> createArticle(@RequestHeader("Authorization")String header, String content, @RequestParam MultipartFile img) throws IOException {
        System.out.println(content);
        String token = jwtTokenProvider.extractToken(header);

        if(!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        ArticleDto articleDto = communityService.createArticle(content, img, jwtTokenProvider.extractMemberId(token));
        return ResponseEntity.ok(articleDto);
    }

    // 커뮤니티 글 전체 조회
    @Operation(summary = "커뮤니티 글 전체 조회")
    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAllCommunityArticles() {
        List<ArticleDto> articleDtos = communityService.getAllCommunityArticles();
        return ResponseEntity.ok(articleDtos);
    }

    // 커뮤니티 글 상세 조회
    @Operation(summary = "커뮤니티 글 상세 조회")
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getCommunityArticle(@PathVariable("articleId") Long articleId) throws NotFoundException {
        ArticleDto articleDto = communityService.getCommunityArticle(articleId);
        return ResponseEntity.ok(articleDto);
    }

    // 커뮤니티 글 수정
    @Operation(summary = "커뮤니티 글 수정")
    @PutMapping("/{articleId}")
    public ResponseEntity<?> updateCommunityArticle(@PathVariable("articleId")Long articleId, @RequestBody ArticleContentDto articleContentDto) {
        ArticleDto articleDto = communityService.updateCommunityArticle(articleId, articleContentDto.getContent());
        return ResponseEntity.ok(articleDto);
    }

    // 커뮤니티 글 삭제
    @Operation(summary = "커뮤니티 글 삭제")
    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteCommunityArticle(@PathVariable("articleId")Long articleId) {
        communityService.deleteCommunityArticle(articleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("DELETE COMMUNITY OK");
    }

    @Operation(summary = "게시글의 전체 평가 수 반환")
    @GetMapping("/{articleId}/review-count")
    public ResponseEntity<ArticleReviewCountDto> countArticleReview(@PathVariable("articleId")Long articleId) {
        return ResponseEntity.ok(communityService.countArticleReview(articleId));
    }

}
