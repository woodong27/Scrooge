package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.community.ArticleGood;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.community.ArticleGoodRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import com.scrooge.scrooge.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityGoodService {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final ArticleGoodRepository articleGoodRepository;
    private final MemberSelectedQuestRepository memberSelectedQuestRepository;
    private final QuestService questService;

    /* 환호/야유 구현*/

    // Article 좋아요를 구현하는 메서드
    @Transactional
    public void addCommunityGood(Long articleId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("해당 멤버를 찾을 수 없습니다."));

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("해당 게시글을 찾을 수 없습니다."));

        ArticleGood articleGood = new ArticleGood();
        articleGood.setMember(member);
        articleGood.setArticle(article);

        if (memberSelectedQuestRepository.existsByMemberIdAndQuestIdAndIsSelected(memberId, 6L, true)) {
            questService.completeQuest(6L, memberId);
        }

        articleGoodRepository.save(articleGood);
    }

    // Article 좋아요 취소를 구현하는 메서드
    @Transactional
    public void cancleCommunityGood(Long articleId, Long memberId) {
        articleGoodRepository.deleteByArticleIdAndMemberId(articleId, memberId);
    }

    // 사용자가 Article을 좋아요 했는지 검사하는 메서드
    public ArticleGoodDto getCommunityGoodCheck(Long articleId, Long memberId) {
        ArticleGood articleGood = articleGoodRepository.findByArticleIdAndMemberId(articleId, memberId).orElse(null);

        ArticleGoodDto articleGoodDto = new ArticleGoodDto();

        if (articleGood == null) {
            articleGoodDto.setGood(false);
            articleGoodDto.setMemberId(memberId);
        } else{
            articleGoodDto.setGood(true);
            articleGoodDto.setMemberId(memberId);
        }

        return articleGoodDto;
    }
}
