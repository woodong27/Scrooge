package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.community.ArticleBad;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.communityDto.ArticleBadDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.community.ArticleBadRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import com.scrooge.scrooge.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityBadService {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final ArticleBadRepository articleBadRepository;
    private final QuestService questService;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;

    // Article 싫어요를 구현하는 메서드
    @Transactional
    public void addCommunityBad(Long articleId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("해당 게시글을 찾을 수 없습니다."));

        ArticleBad articleBad = new ArticleBad();
        articleBad.setMember(member);
        articleBad.setArticle(article);

        if (memberSelectedQuestRepository.existsByMemberIdAndQuestId(memberId, 6L)) {
            questService.completeQuest(6L, memberId);
        }

        articleBadRepository.save(articleBad);
    }

    // Article 싫어요를 취소하는 메서드
    @Transactional
    public void cancelCommunityBad(Long articleId, Long memberId) {
        articleBadRepository.deleteByArticleIdAndMemberId(articleId, memberId);
    }

    // 사용자가 Article 싫어요를 했는지 검사하는 메서드
    public ArticleBadDto getCommunityBadCheck(Long articleId, Long memberId) {
        ArticleBad articleBad = articleBadRepository.findByArticleIdAndMemberId(articleId, memberId).orElse(null);

        ArticleBadDto articleBadDto = new ArticleBadDto();
        if (articleBad == null) {
            articleBadDto.setBad(false);
            articleBadDto.setMemberId(memberId);
        } else {
            articleBadDto.setBad(true);
            articleBadDto.setMemberId(memberId);
        }

        return articleBadDto;
    }
}
