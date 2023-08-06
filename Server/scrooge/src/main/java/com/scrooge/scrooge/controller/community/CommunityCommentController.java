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
@RequestMapping("/community/comment")
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentService communityCommentService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "커뮤니티 댓글 등록")
    @PostMapping("/{articleId}")
    public ResponseEntity<?> createCommunityComment(@RequestHeader("Authorization")String header, @PathVariable("articleId")Long articleId, @RequestBody CommentContentDto commentContentDto) {
        String token = jwtTokenProvider.extractToken(header);

        if(!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }


        ArticleCommentDto commentDto = communityCommentService.createCommunityComment(jwtTokenProvider.extractMemberId(token), articleId, commentContentDto.getContent());

        return ResponseEntity.ok(commentDto);
    }

    @Operation(summary = "커뮤니티 댓글 전체 조회")
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getCommunityComment(@PathVariable("articleId") Long articleId) {
        List<ArticleCommentDto> articleCommentDtoList = communityCommentService.getCommunityComment(articleId);
        return ResponseEntity.ok(articleCommentDtoList);
    }

    @Operation(summary = "커뮤니티 댓글 수정")
    @PutMapping()
    public ResponseEntity<?> updateCommunityComment(@RequestBody ArticleCommentDto articleCommentDto) {
        ArticleCommentDto articleCommentDto1 = communityCommentService.updateCommunityComment(articleCommentDto);
        return ResponseEntity.ok(articleCommentDto1);
    }

    @Operation(summary = "커뮤니티 댓글 삭제")
    @DeleteMapping("/{articleCommentId}")
    public ResponseEntity<?> deleteCommunityComment(@PathVariable("articleCommentId")Long articleCommentId) {
        communityCommentService.deleteCommunityComment(articleCommentId);
        return ResponseEntity.ok("DELETE COMMENT OK");
    }


}
