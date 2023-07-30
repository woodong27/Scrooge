package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.dto.communityDto.ArticleDto;
import com.scrooge.scrooge.service.community.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Tag(name="Community", description = "커뮤니티 API")
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {


    private final CommunityService communityService;

    @Operation(summary = "커뮤니티 글 등록")
    @PostMapping(consumes="multipart/form-data")
    public ResponseEntity<?> createArticle(ArticleDto articleDto, @RequestParam MultipartFile img) {
         communityService.createArticle(articleDto, img);

        SuccessResp successResp = new SuccessResp(1);
        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 커뮤니티 글 전체 조회
    @Operation(summary = "커뮤니티 글 전체 조회")
    @GetMapping
    public ResponseEntity<?> getAllCommunityArticles() {
        List<ArticleDto> articleDtos = communityService.getAllCommunityArticles();
        return ResponseEntity.ok(articleDtos);
    }

    // 커뮤니티 글 상세 조회
    @Operation(summary = "커뮤니티 글 상세 조회")
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getCommunityArticle(@PathVariable("articleId")Long articleId) throws IllegalAccessException {
        ArticleDto articleDto = communityService.getCommunityArticle(articleId);
        return ResponseEntity.ok(articleDto);
    }

    // 커뮤니티 글 삭제
    @Operation(summary = "커뮤니티 글 삭제")
    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteCommunityArticle(@PathVariable("articleId")Long articleId) {
        communityService.deleteCommunityArticle(articleId);
        return ResponseEntity.ok("DELETE COMMUNITY OK");
    }

}
