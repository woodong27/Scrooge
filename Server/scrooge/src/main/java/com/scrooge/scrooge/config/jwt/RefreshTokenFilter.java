package com.scrooge.scrooge.config.jwt;

import antlr.Token;
import com.scrooge.scrooge.domain.member.TokenDto;
import com.scrooge.scrooge.service.member.MemberService;
import com.scrooge.scrooge.service.member.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String expiredAccessToken = extractExpiredAccessToken(request);
        if(expiredAccessToken != null) {
            try {
                Cookie[] cookies = request.getCookies();
                String refreshToken = null;
                if(cookies != null) {
                    for (Cookie cookie : cookies) {
                        if("refreshToken".equals(cookie.getName())) {
                            refreshToken = cookie.getValue();
                            break;
                        }
                    }
                }

                if(refreshToken != null) {
                    TokenDto tokenDto = new TokenDto();
                    tokenDto.setAccessToken(expiredAccessToken);
                    tokenDto.setRefreshToken(refreshToken);

                    TokenDto newTokendTo = tokenService.reIssue(tokenDto, response).getBody();

                    // 새로운 Access Token을 응답 헤더에 설정
                    response.setHeader("Authorization", "Bearer " + newTokendTo.getAccessToken());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);

    }

    private String extractExpiredAccessToken(HttpServletRequest request) {
        // 요청 헤더에서 만료된 Access Token 추출하여 반환
        String header = request.getHeader("Authorization");
        if( header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
