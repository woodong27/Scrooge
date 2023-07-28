package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.community.ArticleBad;
import com.scrooge.scrooge.domain.community.ArticleGood;
import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.dto.communityDto.ArticleBadDto;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import com.scrooge.scrooge.service.CommunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Tag(name="Community", description = "커뮤니티 API")
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    private final ArticleRepository articleRepository;

    /* 환호/야유 기능 구현 */

    // 환호 기능 구현
    @PostMapping("/like")
    public ResponseEntity<?> addCommunityGood(@RequestBody ArticleGoodDto articleGoodDto) {

        SuccessResp successResp = new SuccessResp(1);
        communityService.addCommunityGood(articleGoodDto.getArticleId(), articleGoodDto.getUserId());
        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 환호 취소 구현
    @DeleteMapping("/like")
    public ResponseEntity<?> cancleCommunityGood(@RequestBody ArticleGoodDto articleGoodDto) {

        SuccessResp successResp = new SuccessResp(1);
        communityService.cancleCommunityGood(articleGoodDto.getArticleId(), articleGoodDto.getUserId());
        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 야유 기능 구현
    @PostMapping("/unlike")
    public ResponseEntity<?> addCommunityBad(@RequestBody ArticleBadDto articleBadDto) {

        SuccessResp successResp = new SuccessResp(1);
        communityService.addCommunityBad(articleBadDto.getArticleId(), articleBadDto.getUserId());

        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 아유 기능 취소 구현
    @DeleteMapping("/unlike")
    public ResponseEntity<?> cancelCommunityBad(@RequestBody ArticleBadDto articleBadDto) {

        SuccessResp successResp = new SuccessResp(1);
        communityService.cancelCommunityBad(articleBadDto.getArticleId(), articleBadDto.getUserId());

        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }


}
