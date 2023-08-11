package com.scrooge.scrooge.controller;

import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.dto.AvatarDto;
import com.scrooge.scrooge.dto.member.MemberOwningAvatarRespDto;
import com.scrooge.scrooge.repository.member.MemberOwningAvatarRepository;
import com.scrooge.scrooge.service.AvatarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Avatar APIs", description = "APIs about Badge")
@RestController
@RequestMapping("/api/avatar")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberOwningAvatarRepository memberOwningAvatarRepository;


    @Operation(summary = "Get avatars", description = "Get all avatars")
    @GetMapping("")
    public ResponseEntity<List<AvatarDto>> getAvatars() {
        List<AvatarDto> avatarDtos = avatarService.getAvatars();
        return ResponseEntity.ok(avatarDtos);
    }

    @Operation(summary = "Get avatar", description = "Get one avatar")
    @GetMapping("/{avatarId}")
    public ResponseEntity<AvatarDto> getAvatar (@PathVariable("avatarId") Long avatarId) {
        AvatarDto avatarDto = avatarService.getAvatarById(avatarId);
        return ResponseEntity.ok(avatarDto);
    }

    @Operation(summary = "Select main avatar", description = "Select member's main avatar")
    @PutMapping("/{avatarId}")
    public ResponseEntity<?> selectMainAvatar(@RequestHeader("Authorization")String header, @PathVariable("avatarId")Long avatarId) {
        String token = jwtTokenProvider.extractToken(header);

        if (!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("그런 유저 없습니다.");
        }

        Long memberId = jwtTokenProvider.extractMemberId(token);

        if (!memberOwningAvatarRepository.existsByMemberIdAndAvatarId(memberId, avatarId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 뱃지를 소유하고 있지 않습니다.");
        }

        return ResponseEntity.ok(avatarService.selectMainAvatar(avatarId, memberId));
    }

    // Member가 보유한 아바타 목록 출력
    @Operation(summary = "Member가 보유한 아바타 목록 조회 API")
    @GetMapping("/my-avatar")
    public ResponseEntity<List<MemberOwningAvatarRespDto>> getMemberOwningAvatarList(@RequestHeader("Authorization") String tokenHeader) {
        String token = jwtTokenProvider.extractToken(tokenHeader);
        Long memberId = jwtTokenProvider.extractMemberId(token);

        List<MemberOwningAvatarRespDto> memberOwningAvatarDtoList = avatarService.getMemberOwningAvatarList(memberId);
        return ResponseEntity.ok(memberOwningAvatarDtoList);
    }
}
