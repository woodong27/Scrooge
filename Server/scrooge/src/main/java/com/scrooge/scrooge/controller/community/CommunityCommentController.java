package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import com.scrooge.scrooge.service.community.CommunityCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="CommunityComment", description = "커뮤니티 댓글 등록 API")
@RestController
@RequestMapping("/community/comment")
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentService communityCommentService;

    @Operation(summary = "커뮤니티 댓글 등록")
    @PostMapping
    public ResponseEntity<?> createCommunityComment(@RequestBody ArticleCommentDto articleCommentDto) {
        communityCommentService.createCommunityComment(articleCommentDto);

        return ResponseEntity.ok(articleCommentDto);
    }

}
