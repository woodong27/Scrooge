package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.domain.member.TokenDto;
import com.scrooge.scrooge.service.member.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Tag(name="Token")
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    /* 토큰 재발급 */
    @Operation(summary = "Refresh 토큰을 통해 토큰을 재발급받는다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reIssue(@RequestBody TokenDto tokenDto, HttpServletResponse response) throws Exception {
        TokenDto tokenDto1 = tokenService.reIssue(tokenDto, response).getBody();
        return ResponseEntity.ok(tokenDto1);
    }
}
