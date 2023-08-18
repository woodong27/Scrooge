package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.community.ArticleComment;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import com.scrooge.scrooge.repository.community.ArticleCommentRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import com.scrooge.scrooge.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityCommentService {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final QuestService questService;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;


    @Transactional
    public ArticleCommentDto createCommunityComment(Long memberId, Long articleId, String content) throws NotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("해당 게시글을 찾을 수 없습니다."));

        // 댓글 5개 이상 작성 퀘스트
        if (memberSelectedQuestRepository.existsByMemberIdAndQuestIdAndIsSelected(memberId, 3L, true)) {
            questService.completeQuest(3L, memberId);
        }

        ArticleComment articleComment = new ArticleComment();
        articleComment.setContent(content);
        articleComment.setMember(member);
        articleComment.setArticle(article);
        articleCommentRepository.save(articleComment);

        return new ArticleCommentDto(articleComment);
    }

    // articleId에 해당하는 글의 전체 댓글 조회 API
    public List<ArticleCommentDto> getCommunityComment(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("해당 게시글을 찾을 수 없습니다."));

        List<ArticleCommentDto> articleCommentDtos = article.getArticleComments().stream()
                .map(ArticleCommentDto::new)
                .collect(Collectors.toList());

        articleCommentDtos.sort(Comparator.comparing(ArticleCommentDto::getId).reversed());
        return articleCommentDtos;
    }

    // 댓글 수정하는 API
    public ArticleCommentDto updateCommunityComment(Long articleCommentId, String content) {
        ArticleComment articleComment = articleCommentRepository.findById(articleCommentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        // 새로운 정보 업데이트
        articleComment.setContent(content);
        articleCommentRepository.save(articleComment);

        return new ArticleCommentDto(articleComment);
    }

    // 댓글 삭제하는 API
    public void deleteCommunityComment(Long articleCommentId) {
        ArticleComment articleComment = articleCommentRepository.findById(articleCommentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        articleCommentRepository.delete(articleComment);
    }
}
