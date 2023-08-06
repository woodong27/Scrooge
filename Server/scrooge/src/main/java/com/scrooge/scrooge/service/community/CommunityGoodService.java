package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.ArticleGood;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.repository.community.ArticleGoodRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import com.scrooge.scrooge.repository.member.MemberSelectedQuestRepository;
import com.scrooge.scrooge.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public ArticleGood addCommunityGood(Long articleId, Long memberId) {
        ArticleGood articleGood = new ArticleGood();

        articleGood.setMember(memberRepository.findById(memberId).orElse(null));
        articleGood.setArticle(articleRepository.findById(articleId).orElse(null));

        if (memberSelectedQuestRepository.existsByMemberIdAndQuestId(memberId, 6L)) {
            questService.completeQuest(6L, memberId);
        }

        return articleGoodRepository.save(articleGood);
    }

    // Article 좋아요 취소를 구현하는 메서드
    @Transactional
    public void cancleCommunityGood(Long articleId, Long memberId) {
        ArticleGood articleGood = articleGoodRepository.findByArticleIdAndMemberId(articleId, memberId);
        if(articleGood != null) {
            articleGoodRepository.delete(articleGood);
        }
    }

    // 사용자가 Article을 좋아요 했는지 검사하는 메서드
    public Integer getCommunityGoodCheck(ArticleGoodDto articleGoodDto) {
        ArticleGood articleGood = articleGoodRepository.findByArticleIdAndMemberId(articleGoodDto.getArticleId(), articleGoodDto.getMemberId());
        if (articleGood != null) return 1;
        else return 0;
    }

    // Article의 전체 좋아요 수 조회하는 메서드
    public Integer getCommunityGoodCount(Long articleId) {
        return articleGoodRepository.countByArticleId(articleId);
    }
}
