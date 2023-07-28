package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.ArticleBad;
import com.scrooge.scrooge.dto.communityDto.ArticleBadDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.repository.community.ArticleBadRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityBadService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleBadRepository articleBadRepository;

    // Article 싫어요를 구현하는 메서드
    @Transactional
    public ArticleBad addCommunityBad(Long articleId, Long userId) {
        ArticleBad articleBad = new ArticleBad();

        articleBad.setUser(userRepository.findById(userId).orElse(null));
        articleBad.setArticle(articleRepository.findById(articleId).orElse(null));

        return articleBadRepository.save(articleBad);
    }

    // Article 싫어요를 취소하는 메서드
    public void cancelCommunityBad(Long articleId, Long userId) {
        ArticleBad articleBad = articleBadRepository.findByArticleIdAndUserId(articleId, userId);

        if(articleBad != null) {
            articleBadRepository.delete(articleBad);
        }
    }

    // 사용자가 Article 싫어요를 했는지 검사하는 메서드
    public Integer getCommunityBadCheck(ArticleBadDto articleBadDto) {
        ArticleBad articleBad = articleBadRepository.findByArticleIdAndUserId(articleBadDto.getArticleId(), articleBadDto.getUserId());

        if(articleBad != null) return 1;
        else return 0;
    }

    // Article의 전체 싫어요 수 조회하는 메서드
    public Integer getCommunityBadCount(Long articleId) {
        return articleBadRepository.countByArticleId(articleId);
    }
}
