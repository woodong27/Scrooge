package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="CommunityLike", description = "커뮤니티 좋아요 관련 API")
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityGoodController {

    private final CommunityService communityService;

    // 환호 기능 구현
    @Operation(summary = "커뮤니티 글 좋아요 등록")
    @PostMapping("/like")
    public ResponseEntity<?> addCommunityGood(@RequestBody ArticleGoodDto articleGoodDto) {

        SuccessResp successResp = new SuccessResp(1);
        communityService.addCommunityGood(articleGoodDto.getArticleId(), articleGoodDto.getUserId());
        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 환호 취소 구현
    @Operation(summary = "커뮤니티 글 좋아요 취소")
    @DeleteMapping("/like")
    public ResponseEntity<?> cancleCommunityGood(@RequestBody ArticleGoodDto articleGoodDto) {

        SuccessResp successResp = new SuccessResp(1);
        communityService.cancleCommunityGood(articleGoodDto.getArticleId(), articleGoodDto.getUserId());
        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 사용자가 환호를 했는지 체크하기
    @Operation(summary = "사용자가 커뮤니티 글을 좋아요 했는지 체크")
    @GetMapping("/like-check")
    public ResponseEntity<?> getCommunityGoodCheck(@RequestBody ArticleGoodDto articleGoodDto) throws Exception {
        Integer result = communityService.getCommunityGoodCheck(articleGoodDto);
        if (result >= 0) return new ResponseEntity<Integer>(result, HttpStatus.OK);
        else throw new Exception();
    }

    // 글 전체 환호 수 조회
    @Operation(summary = "커뮤니티 글 좋아요 전체 수 조회")
    @GetMapping("/like-count/{articleId}")
    public ResponseEntity<?> getCommunityGoodCount(@PathVariable("articleId")Long articleId) {
        Integer result = communityService.getCommunityGoodCount(articleId);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }


}
