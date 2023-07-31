package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.ArticleComment;
import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.repository.community.ArticleCommentRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityCommentService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;


    @Transactional
    public void createCommunityComment(ArticleCommentDto articleCommentDto) {
        ArticleComment articleComment = new ArticleComment();

        articleComment.setContent(articleCommentDto.getContent());

        // 연결
        articleComment.setUser(userRepository.findById(articleCommentDto.getUserId()).orElse(null));
        articleComment.setArticle(articleRepository.findById(articleCommentDto.getArticleId()).orElse(null));

        articleCommentRepository.save(articleComment);
    }

    // articleId에 해당하는 글의 전체 댓글 조회 API
    public List<ArticleCommentDto> getCommunityComment(Long articleId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<ArticleComment> articleComments = articleCommentRepository.findByArticleId(sort, articleId);
        return articleComments.stream()
                .map(articleComment -> {
                    ArticleCommentDto articleCommentDto = new ArticleCommentDto();
                    articleCommentDto.setId(articleComment.getId());
                    articleCommentDto.setContent(articleComment.getContent());
                    articleCommentDto.setCreatedAt(articleComment.getCreatedAt());

                    // user 관련 정보
                    articleCommentDto.setUserId(articleComment.getUser().getId()); // 필요 X?
                    articleCommentDto.setNickname(articleComment.getUser().getNickname());
                    articleCommentDto.setAvatarImgAddress(articleComment.getUser().getMainAvatar().getImgAddress());

                    // article 관련 정보
                    articleCommentDto.setArticleId(articleComment.getArticle().getId());

                    return articleCommentDto;
                })
                .collect(Collectors.toList());
    }
}
