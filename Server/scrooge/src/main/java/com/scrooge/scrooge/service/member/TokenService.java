package com.scrooge.scrooge.service.member;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.domain.member.TokenDto;
import com.scrooge.scrooge.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    /* Refresh Token 구현 */
    @Transactional
    public ResponseEntity<TokenDto> reIssue(TokenDto tokenDto, HttpServletResponse response) throws Exception {
        if(!jwtTokenProvider.validateToken(tokenDto.getRefreshToken()))
            throw new Exception("유효하지 않은 Token 입니다.");
        Member member = memberRepository.findByRefreshToken(tokenDto.getRefreshToken());

        String accessToken = jwtTokenProvider.createToken(member.getEmail(), member.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        member.updateRefreshToken(refreshToken);

        TokenDto newTokenDto = new TokenDto();
        newTokenDto.setAccessToken(accessToken);
        newTokenDto.setRefreshToken(refreshToken);

        // refreshToken을 쿠키로 설정하여 브라우저에 저장
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(newTokenDto);
    }

}
