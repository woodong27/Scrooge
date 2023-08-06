package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.dto.communityDto.ArticleBadDto;
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
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityBadController {

    private final CommunityBadService communityBadService;
    private final MemberOwningBadgeRepository memberOwningBadgeRepository;
    private final BadgeService badgeService;

    // 야유 기능 구현
    @PostMapping("/unlike")
    public ResponseEntity<?> addCommunityBad(@RequestBody ArticleBadDto articleBadDto) {

        // 게시글 첫 평가 뱃지
        if (!memberOwningBadgeRepository.existsByBadgeIdAndMemberId(6L, articleBadDto.getMemberId())) {
            badgeService.giveBadge(6L, articleBadDto.getMemberId());
        }

        SuccessResp successResp = new SuccessResp(1);
        communityBadService.addCommunityBad(articleBadDto.getArticleId(), articleBadDto.getMemberId());

        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 아유 기능 취소 구현
    @DeleteMapping("/unlike")
    public ResponseEntity<?> cancelCommunityBad(@RequestBody ArticleBadDto articleBadDto) {

        SuccessResp successResp = new SuccessResp(1);
        communityBadService.cancelCommunityBad(articleBadDto.getArticleId(), articleBadDto.getMemberId());

        return new ResponseEntity<>(successResp, HttpStatus.OK);
    }

    // 사용자가 아유를 했는지 체크하기
    @GetMapping("/unlike-check")
    public ResponseEntity<?> getCommunityBadCheck(@RequestBody ArticleBadDto articleBadDto) throws Exception{
        Integer result = communityBadService.getCommunityBadCheck(articleBadDto);
        if(result >= 0) return new ResponseEntity<Integer>(result, HttpStatus.OK);
        else throw new Exception();
    }

    // 글 전체 싫어요 수 조회
    @GetMapping("/unlike-count/{articleId}")
    public ResponseEntity<?> getCommunityBadCount(@PathVariable("articleId")Long articleId) {
        Integer result = communityBadService.getCommunityBadCount(articleId);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
