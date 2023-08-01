package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.dto.LoginRequestDto;
import com.scrooge.scrooge.dto.member.MemberDto;
import com.scrooge.scrooge.dto.SignUpRequestDto;
import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.repository.member.MemberRepository;
import com.scrooge.scrooge.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Member", description = "Member API")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Operation(summary = "회원가입 API", description = "회원가입 POST")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        memberService.signUp(signUpRequestDto);
        return ResponseEntity.ok("회원가입 완료");
    }

    @Operation(summary = "로그인 API", description = "로그인 POST")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = memberService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "멤버정보 API", description = "멤버정보 GET")
    @GetMapping("/info")
    public ResponseEntity<MemberDto> getUserInfo(@RequestHeader("Authorization") String tokenHeader) {
       String token = extractToken(tokenHeader);

       if (!jwtTokenProvider.validateToken(token)) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }

       String email = jwtTokenProvider.extractEmail(token);
       Optional<MemberDto> memberDto = memberService.getInfo(email);
       return memberDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    private String extractToken(String header) {
        if (header != null && header.startsWith("Bearer")) {
            return header.substring(7);
        }
        return null;
    }

}
