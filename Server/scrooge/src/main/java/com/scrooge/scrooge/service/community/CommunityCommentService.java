package com.scrooge.scrooge.service.community;

import com.scrooge.scrooge.domain.community.ArticleComment;
import com.scrooge.scrooge.dto.communityDto.ArticleCommentDto;
import com.scrooge.scrooge.repository.UserRepository;
import com.scrooge.scrooge.repository.community.ArticleCommentRepository;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    // 댓글 수정하는 API
    public ArticleCommentDto updateCommunityComment(ArticleCommentDto articleCommentDto) {
        Optional<ArticleComment> articleComment = articleCommentRepository.findById(articleCommentDto.getId());
        if(articleComment.isEmpty()) {
            throw new NotFoundException(articleCommentDto.getId() + "의 ID를 가진 댓글을 찾을 수 없습니다.");
        }
        // 새로운 정보 업데이트
        articleComment.get().setContent(articleCommentDto.getContent());

        // DB에 새로운 댓글 저장
        ArticleComment updatedComment = articleCommentRepository.save(articleComment.get());
        return new ArticleCommentDto(updatedComment);

    }
}
