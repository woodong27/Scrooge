package com.scrooge.scrooge.config.jwt;

import antlr.Token;
import com.scrooge.scrooge.domain.member.TokenDto;
import com.scrooge.scrooge.service.member.MemberService;
import com.scrooge.scrooge.service.member.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.print("만료된 토큰을 갱신해보아용");
        
        String expiredAccessToken = extractExpiredAccessToken(request);

        if(expiredAccessToken != null) {
            System.out.println("유효한 토큰인가용?");

            try {

                if(!jwtTokenProvider.validateToken(expiredAccessToken)) {

                    System.out.println("토큰이 만료되었네용 ,, ");

                    Cookie[] cookies = request.getCookies();

                    String refreshToken = null;
                    if(cookies != null) {
                        for (Cookie cookie : cookies) {
                            if("refreshToken".equals(cookie.getName())) {
                                refreshToken = cookie.getValue();
                                System.out.println("리프레시 토큰 : " + refreshToken);
                                break;
                            }
                        }
                    }

                    if(refreshToken != null) {
                        TokenDto tokenDto = new TokenDto();
                        tokenDto.setAccessToken(expiredAccessToken);
                        tokenDto.setRefreshToken(refreshToken);

                        TokenDto newTokendTo = tokenService.reIssue(tokenDto, response).getBody();

                        System.out.println("새로운 accessToken: " + newTokendTo.getAccessToken());
                }
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
