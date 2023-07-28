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
import org.apache.coyote.Response;
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

    // 사용자가 환호를 했는지 체크하기
    @GetMapping("/like-check")
    public ResponseEntity<?> getCommunityGoodCheck(@RequestBody ArticleGoodDto articleGoodDto) throws Exception {
        Integer result = communityService.getCommunityGoodCheck(articleGoodDto);
        if (result >= 0) return new ResponseEntity<Integer>(result, HttpStatus.OK);
        else throw new Exception();
    }

    // 글 전체 환호 수 조회
    @GetMapping("/like-count/{articleId}")
    public ResponseEntity<?> getCommunityGoodCount(@PathVariable("articleId")Long articleId) {
        Integer result = communityService.getCommunityGoodCount(articleId);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
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

    // 사용자가 아유를 했는지 체크하기
    @GetMapping("/unlike-check")
    public ResponseEntity<?> getCommunityBadCheck(@RequestBody ArticleBadDto articleBadDto) throws Exception{
        Integer result = communityService.getCommunityBadCheck(articleBadDto);
        if(result >= 0) return new ResponseEntity<Integer>(result, HttpStatus.OK);
        else throw new Exception();
    }

    // 글 전체 싫어요 수 조회
    @GetMapping("/unlike-count/{articleId}")
    public ResponseEntity<?> getCommunityBadCount(@PathVariable("articleId")Long articleId) {
        Integer result = communityService.getCommunityBadCount(articleId);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }


}
