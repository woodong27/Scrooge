package com.scrooge.scrooge.controller.community;

import com.scrooge.scrooge.domain.community.Article;
import com.scrooge.scrooge.domain.community.ArticleBad;
import com.scrooge.scrooge.domain.community.ArticleGood;
import com.scrooge.scrooge.dto.SuccessResp;
import com.scrooge.scrooge.dto.communityDto.ArticleBadDto;
import com.scrooge.scrooge.dto.communityDto.ArticleGoodDto;
import com.scrooge.scrooge.repository.community.ArticleRepository;
import com.scrooge.scrooge.service.CommunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Tag(name="Community", description = "커뮤니티 API")
@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

}
