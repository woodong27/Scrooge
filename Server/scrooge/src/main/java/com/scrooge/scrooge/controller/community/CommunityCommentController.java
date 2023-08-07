package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import com.scrooge.scrooge.dto.communityDto.CommentContentDto;
import com.scrooge.scrooge.service.community.CommunityCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Tag(name="CommunityComment", description = "커뮤니티 댓글 등록 API")
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentService communityCommentService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "커뮤니티 댓글 등록")
    @PostMapping("/{articleId}/comment")
    public ResponseEntity<?> createCommunityComment(@RequestHeader("Authorization")String header, @PathVariable("articleId")Long articleId, @RequestBody CommentContentDto commentContentDto) {
        String token = jwtTokenProvider.extractToken(header);

        if(!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }


        ArticleCommentDto commentDto = communityCommentService.createCommunityComment(jwtTokenProvider.extractMemberId(token), articleId, commentContentDto.getContent());

        return ResponseEntity.ok(commentDto);
    }

    @Operation(summary = "커뮤니티 댓글 전체 조회")
    @GetMapping("/{articleId}/comment")
    public ResponseEntity<?> getCommunityComment(@PathVariable("articleId") Long articleId) {
        List<ArticleCommentDto> articleCommentDtoList = communityCommentService.getCommunityComment(articleId);
        return ResponseEntity.ok(articleCommentDtoList);
    }

    @Operation(summary = "커뮤니티 댓글 수정")
    @PutMapping("/comment/{articleCommentId}")
    public ResponseEntity<?> updateCommunityComment(@PathVariable("articleCommentId")Long articleCommentId, @RequestBody CommentContentDto commentContentDto) {
        ArticleCommentDto articleCommentDto = communityCommentService.updateCommunityComment(articleCommentId, commentContentDto.getContent());
        return ResponseEntity.ok(articleCommentDto);
    }

    @Operation(summary = "커뮤니티 댓글 삭제")
    @DeleteMapping("/comment/{articleCommentId}")
    public ResponseEntity<?> deleteCommunityComment(@PathVariable("articleCommentId") Long articleCommentId) {
        communityCommentService.deleteCommunityComment(articleCommentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("댓글 삭제 완료");
    }
}
