package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.domain.member.Member;
import com.scrooge.scrooge.dto.MemberDto;
import com.scrooge.scrooge.dto.SignUpRequestDto;
import com.scrooge.scrooge.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Member", description = "Member API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API", description = "회원가입 POST")
    @PostMapping("/signup")
    public ResponseEntity<Member> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        Member member = memberService.signUp(signUpRequestDto);
        return ResponseEntity.ok(member);
    }

    @Operation(summary = "유저 정보를 가져오는 API", description = "유저 정보 GET")
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDto> getUserInfo(@PathVariable("memberId") Long memberId) {
        Optional<MemberDto> memberDto = memberService.getMemberInfo(memberId);
        return memberDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
