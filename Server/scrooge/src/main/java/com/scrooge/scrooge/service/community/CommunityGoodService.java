package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.ArticleGood;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.repository.community.ArticleBadRepository;
import com.scrooge.scrooge.repository.community.ArticleGoodRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityGoodService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleGoodRepository articleGoodRepository;

    /* 환호/야유 구현*/

    // Article 좋아요를 구현하는 메서드
    @Transactional
    public ArticleGood addCommunityGood(Long articleId, Long userId) {
        ArticleGood articleGood = new ArticleGood();

        articleGood.setUser(userRepository.findById(userId).orElse(null));
        articleGood.setArticle(articleRepository.findById(articleId).orElse(null));

        return articleGoodRepository.save(articleGood);
    }

    // Article 좋아요 취소를 구현하는 메서드
    @Transactional
    public void cancleCommunityGood(Long articleId, Long userId) {
        ArticleGood articleGood = articleGoodRepository.findByArticleIdAndUserId(articleId, userId);
        if(articleGood != null) {
            articleGoodRepository.delete(articleGood);
        }
    }

    // 사용자가 Article을 좋아요 했는지 검사하는 메서드
    public Integer getCommunityGoodCheck(ArticleGoodDto articleGoodDto) {
        ArticleGood articleGood = articleGoodRepository.findByArticleIdAndUserId(articleGoodDto.getArticleId(), articleGoodDto.getUserId());
        if (articleGood != null) return 1;
        else return 0;
    }

    // Article의 전체 좋아요 수 조회하는 메서드
    public Integer getCommunityGoodCount(Long articleId) {
        return articleGoodRepository.countByArticleId(articleId);
    }
}
